package it.sevenbits.eisetasks.core.service;

import it.sevenbits.eisetasks.core.model.Task;
import it.sevenbits.eisetasks.core.repository.tasks.ITasksRepository;
import it.sevenbits.eisetasks.web.model.tasks.GetAllTasksResponse;
import it.sevenbits.eisetasks.web.model.tasks.Pagination;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

public class TasksServiceTest {
    private ITasksRepository tasksRepository;
    private TasksService tasksService;
    private String status = "inbox";
    private String order = "asc";
    private String owner = "owner";
    private Pagination pagination = new Pagination(1,
            15,
            15,
            1,
            order);

    @Before
    public void setup() {
        tasksRepository = mock(ITasksRepository.class);
        tasksService = new TasksService(tasksRepository, pagination);
    }

    @Test
    public void testGetTasksWithPagination() {
        List<Task> tasksList = new ArrayList<>();
        tasksList.add(mock(Task.class));
        tasksList.add(mock(Task.class));
        tasksList.add(mock(Task.class));
        when(tasksRepository.getTasksWithPagination(anyString(), anyString(), anyInt(), anyInt(), anyString()))
                .thenReturn(tasksList);
        when(tasksRepository.count(anyString(), anyString())).thenReturn(3);
        GetAllTasksResponse response = tasksService.getTasksWithPagination(status,
                order,
                2,
                1,
                owner);
        assertEquals(tasksList, response.getTasks());
        assertEquals(2, response.getMeta().getPage());
        assertEquals(1, response.getMeta().getSize());
        assertEquals(3, response.getMeta().getTotal());
        assertEquals("/tasks?status=inbox&order=asc&page=1&size=1", response.getMeta().getFirst());
        assertEquals("/tasks?status=inbox&order=asc&page=3&size=1", response.getMeta().getLast());
        assertEquals("/tasks?status=inbox&order=asc&page=3&size=1", response.getMeta().getNext());
        assertEquals("/tasks?status=inbox&order=asc&page=1&size=1", response.getMeta().getPrev());
    }
}