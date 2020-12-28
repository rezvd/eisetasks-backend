package it.sevenbits.eisetasks.core.repository.tasks;

import it.sevenbits.eisetasks.core.model.Task;

import java.util.List;

/**
 * Interface for tasks repository
 */
public interface ITasksRepository {

    /**
     * Function for getting all tasks with certain status and pagination
     *
     * @param status   is for selection tasks
     * @param order    is he order to sort task. Can have values "desc" or "asc"
     * @param page     is the number of current page. Starts with 1
     * @param pageSize is the size of the page
     * @param owner    is the ID of user whose task are needed
     * @return list of tasks on current page with certain status in certain order.
     * Size of the list cannot be greater then pageSize
     */
    List<Task> getTasksWithPagination(String status, String order, int page, int pageSize, String owner);

    /**
     * Created new Task with selected text. Other parameters of task will be set by default
     *
     * @param text is the text of future task
     * @param owner is an ID of user who owns this task
     * @return created task with selected text
     */
    Task create(String text, String owner);

    /**
     * Search task with this id through repository
     *
     * @param id is the id of needed task
     * @return found task or null, if there is no task with such id
     */
    Task getById(String id);

    /**
     * Changes existing task with text and status of newTask.
     * Field updatedAt is also will be changes according to the current time
     *
     * @param newTask is the task, which id will be used to find existing task and
     *                which text and status will be used to update current task
     * @param previousTask is old task data to keep old information in fields which don't have new data
     */
    void update(Task newTask, Task previousTask);

    /**
     * Deletes tasks with this id
     *
     * @param id is id of task, which will be deleted
     */
    void delete(String id);

    /**
     * Calculate number of tasks with certain status
     *
     * @param status is the status to select task
     * @param owner is the id of owner whose tasks will be counted
     * @return number of tasks with this status
     */
    int count(String status, String owner);
}
