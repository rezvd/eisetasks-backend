package it.sevenbits.eisetasks.core.repository.tasks;

import org.springframework.jdbc.core.JdbcOperations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TasksRepositoryDBTest {
    private JdbcOperations mockJdbc;
    private TasksRepositoryDB tasksRepository;

    String status = "inbox";
    String text = "Do homework";
    String order = "DESC";
    String owner = "owner";
    int page = 1;
    int pageSize = 25;
    int count = 1;

/*

    @Before
    public void setup() {
        mockJdbc = mock(JdbcOperations.class);
        tasksRepository = new TasksRepositoryDB(mockJdbc);
    }

    @Test
    public void getAllTasksTest() {
        List<Task> mockTasks = mock(List.class);

        when(mockJdbc.query(anyString(), any(RowMapper.class), anyString(), anyString(), anyInt(), anyInt())).thenReturn(mockTasks);

        List<Task> actualList = tasksRepository.getTasksWithPagination(status, order, page, pageSize, owner);

        verify(mockJdbc, times(1)).query(
                eq(String.format("SELECT id, text, status, createdAt, updatedAt, owner FROM task "
                        + "WHERE status = ? and owner = ? ORDER BY createdAt %s OFFSET ? LIMIT ?", order)),
                any(RowMapper.class),
                eq(status),
                eq(owner),
                eq((page - 1) * pageSize),
                eq(pageSize)
        );

        Assert.assertSame(mockTasks, actualList);
    }


    @Test
    public void getTaskByIdTest() {
        String id = UUID.randomUUID().toString();
        Task task = mock(Task.class);

        when(mockJdbc.queryForObject(anyString(), any(RowMapper.class), anyString())).thenReturn(task);

        Task actual = tasksRepository.getById(id);
        verify(mockJdbc, times(1)).queryForObject(
                eq("SELECT id, text, status, createdAt, updatedAt FROM task WHERE id = ?"),
                any(RowMapper.class),
                eq(id)
        );

        Assert.assertSame(task, actual);
    }

    @Test
    public void updateTest() {
        String id = UUID.randomUUID().toString();
        Date date = new Date();

        tasksRepository.update(new Task(id, text, status, null, date, owner));

        verify(mockJdbc, times(1)).update(
                eq("UPDATE task SET text = ?, status = ?,  updatedAt = ? WHERE id = ?"),
                eq(text),
                eq(status),
                eq(date),
                eq(id)
        );
    }

    @Test
    public void deleteTest() {
        String id = UUID.randomUUID().toString();

        tasksRepository.delete(id);

        verify(mockJdbc, times(1)).update(
                eq("DELETE from Task WHERE id = ?"),
                eq(id)
        );
    }

    @Test
    public void countTest() {
        when(mockJdbc.queryForObject(anyString(), eq(Integer.class), anyString())).thenReturn(count);

        int actual = tasksRepository.count(status);
        verify(mockJdbc, times(1)).queryForObject(
                eq("SELECT COUNT (*) FROM task WHERE status = ?"),
                eq(Integer.class),
                eq(status)
        );

        Assert.assertSame(count, actual);
    }


    @Test
    public void createTest() {
        Task actual = tasksRepository.create(text, owner);
        Assert.assertEquals(actual.getText(), text);
        Assert.assertEquals(actual.getStatus(), status);
        Assert.assertEquals(actual.getCreatedAt(), actual.getUpdatedAt());
    }

 */
}