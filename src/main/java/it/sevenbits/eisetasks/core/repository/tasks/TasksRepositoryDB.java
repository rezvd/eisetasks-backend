package it.sevenbits.eisetasks.core.repository.tasks;

import it.sevenbits.eisetasks.core.model.Task;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Implementation of ITasksRepository for work with database with JdbcOperations
 */
public class TasksRepositoryDB implements ITasksRepository {
    private final JdbcOperations jdbcOperations;

    /**
     * Constructor for repository
     *
     * @param jdbcOperations provides operation for work with database
     */
    public TasksRepositoryDB(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Task> getTasksWithPagination(final String status,
                                             final String order,
                                             final int page,
                                             final int pageSize,
                                             final String owner) {
        return jdbcOperations.query(String.format("SELECT id, text, status, createdAt, updatedAt, owner FROM task "
                        + "WHERE status = ? and owner = ? ORDER BY createdAt %s OFFSET ? LIMIT ?",
                order.toUpperCase()),
                (resultSet, i) -> buildTask(resultSet),
                status, owner, (page - 1) * pageSize, pageSize);
    }

    /**
     * Creates task with selected text. Status is "inbox" by default. ID is provided by method getNextID.
     * Fields createdAt and updatedAt is set according to the current date and time
     *
     * @param text is the text of future task
     * @return created task
     */
    @Override
    public Task create(final String text, final String owner) {
        String id = getNextID();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        jdbcOperations.update(
                "INSERT INTO task (id, text, status, createdAt, updatedAt, owner) VALUES (?, ?, ?, ?, ?, ?)",
                id, text, "inbox", date, date, owner
        );
        return new Task(id, text, "inbox", date, date, owner);
    }

    /**
     * Search task with this id through repository
     *
     * @param id is the id of needed task
     * @return found task or null, if there is no task with such id
     */
    @Override
    public Task getById(final String id) {
        try {
            return jdbcOperations.queryForObject(
                    "SELECT id, text, status, createdAt, updatedAt, owner FROM task WHERE id = ?",
                    (resultSet, i) -> buildTask(resultSet),
                    id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Changes existing task with text and status of newTask.
     * Field updatedAt is also will be changes according to the current time
     *
     * @param newTask is the task, which id will be used to find existing task and
     *                which text and status will be used to update current task
     */
    @Override
    public void update(final Task newTask, final Task previousTask) {
        jdbcOperations.update(
                "UPDATE task SET text = COALESCE(?, ?)," +
                        "status = COALESCE(?, ?),  updatedAt = ? WHERE id = ?",
                newTask.getText(), previousTask.getText(),
                newTask.getStatus(), previousTask.getStatus(),
                newTask.getUpdatedAt(), newTask.getId()
        );
    }

    /**
     * Deletes tasks with this id
     *
     * @param id is id of task, which will be deleted
     */
    @Override
    public void delete(final String id) {
        jdbcOperations.update("DELETE from Task WHERE id = ?", id);
    }

    @Override
    public int count(final String status,
                     final String owner) {
        return jdbcOperations.queryForObject("SELECT COUNT (*) FROM task WHERE status = ? AND owner = ?",
                Integer.class, status, owner);
    }

    /**
     * Generates new id
     *
     * @return new random id
     */
    private String getNextID() {
        return UUID.randomUUID().toString();
    }

    private Task buildTask(final ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String name = resultSet.getString("text");
        String currentStatus = resultSet.getString("status");
        Date createdAt = resultSet.getTimestamp("createdAt");
        Date updatedAt = resultSet.getTimestamp("updatedAt");
        String owner = resultSet.getString("owner");
        return new Task(id, name, currentStatus, createdAt, updatedAt, owner);
    }
}
