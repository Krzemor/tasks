package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TasksControllerTest {

    private DbService dbService;
    private TaskMapper taskMapper;
    private TasksController tasksController;

    @BeforeEach
    void setUp() {
        dbService = mock(DbService.class);
        taskMapper = mock(TaskMapper.class);
        tasksController = new TasksController(dbService, taskMapper);
    }

    @Test
    void getAllTasks() {
        //Given
        List<Task> tasks = List.of(new Task(1L, "test", "Content"));
        List<TaskDto> taskDtos = List.of(new TaskDto(1L, "test", "Content"));

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);

        //When
        ResponseEntity<List<TaskDto>> response = tasksController.getTasks();

        //Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("test", response.getBody().get(0).getTitle());
    }

    @Test
    void getTaskById() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "test", "Content");
        TaskDto taskDto = new TaskDto(1L, "test", "Content");

        when(dbService.getTaskById(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When
        ResponseEntity<TaskDto> response = tasksController.getTaskById(1L);

        //Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("test", response.getBody().getTitle());
    }

    @Test
    void deleteTask() {
        //Given
        doNothing().when(dbService).deleteTask(1L);

        //When
        ResponseEntity<Void> response = tasksController.deleteTask(1L);

        //Then
        assertEquals(204, response.getStatusCodeValue());
        verify(dbService, times(1)).deleteTask(1L);
    }

    @Test
    void updateTask() {
        //Given
        Task task = new Task(1L, "updated", "Content");
        TaskDto taskDto = new TaskDto(1L, "updated", "Content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When
        ResponseEntity<TaskDto> response = tasksController.updateTask(taskDto);

        //Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("updated", response.getBody().getTitle());
    }

    @Test
    void createTask() {
        //Given
        Task task = new Task(null, "test", "Content");
        TaskDto taskDto = new TaskDto(null, "test", "Content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);

        //When
        ResponseEntity<Void> response = tasksController.createTask(taskDto);

        //Then
        assertEquals(200, response.getStatusCodeValue());
        verify(dbService, times(1)).saveTask(task);
    }
}