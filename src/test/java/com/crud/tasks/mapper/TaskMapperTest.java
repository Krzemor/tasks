package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TaskMapperTest {

    private final TaskMapper taskMapper = new TaskMapper();

    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "TaskDto", "Content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertThat(task.getId()).isEqualTo(1L);
        assertThat(task.getTitle()).isEqualTo("TaskDto");
        assertThat(task.getContent()).isEqualTo("Content");
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task(1L, "Task", "Content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertThat(taskDto.getId()).isEqualTo(1L);
        assertThat(taskDto.getTitle()).isEqualTo("Task");
        assertThat(taskDto.getContent()).isEqualTo("Content");
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        List<Task> tasks = List.of(
                new Task(1L,"Task 1", "Content 1"),
                new Task(2L,"Task 2", "Content 2"),
                new Task(3L,"Task 3", "Content 3")
        );

        //When
        List<TaskDto> listDTo = taskMapper.mapToTaskDtoList(tasks);

        //Then
        assertThat(listDTo.size()).isEqualTo(3);

        assertThat(listDTo.get(0).getId()).isEqualTo(1L);
        assertThat(listDTo.get(0).getTitle()).isEqualTo("Task 1");
        assertThat(listDTo.get(0).getContent()).isEqualTo("Content 1");

        assertThat(listDTo.get(1).getId()).isEqualTo(2L);
        assertThat(listDTo.get(1).getTitle()).isEqualTo("Task 2");
        assertThat(listDTo.get(1).getContent()).isEqualTo("Content 2");

        assertThat(listDTo.get(2).getId()).isEqualTo(3L);
        assertThat(listDTo.get(2).getTitle()).isEqualTo("Task 3");
        assertThat(listDTo.get(2).getContent()).isEqualTo("Content 3");
    }
}