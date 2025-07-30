package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DbServiceTest {

    private DbService dbService;
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        dbService = new DbService(taskRepository);
    }

    @Test
    void shouldGetAllTasks() {
        //Given
        List<Task> mockTasks = List.of(
                new Task(1L, "task 1", "content 1"),
                new Task(2L, "task 2", "content 2")
        );
        when(taskRepository.findAll()).thenReturn(mockTasks);

        //When
        List<Task> result = dbService.getAllTasks();

        //Then
        assertEquals(2, result.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void shouldGetTaskByIdWhenTaskExists() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "task 1", "content 1");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        //When
        Task result = dbService.getTaskById(1L);

        //Then
        assertEquals("task 1", result.getTitle());
        verify(taskRepository).findById(1L);
    }

    @Test
    void shouldSaveTask() {
        //Given
        Task task = new Task(null, "task 1", "content 1");
        Task savedTask = new Task(1L, "task 1", "content 1");
        when(taskRepository.save(task)).thenReturn(savedTask);

        //When
        Task result = dbService.saveTask(task);

        //Then
        assertEquals(1L, result.getId());
        verify(taskRepository).save(task);
    }

    @Test
    void shouldDeleteTask() {
        //When
        dbService.deleteTask(1L);

        //then
        verify(taskRepository).deleteById(1L);
    }
}