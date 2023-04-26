package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTest {

    private final TaskMapper taskMapper = new TaskMapper();

    @Test
    void testMapToTask() {

        //Given
        TaskDto taskDto = new TaskDto("title", "content");

        //Then
        Task task = taskMapper.mapToTask(taskDto);

        //When
        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getTitle(), taskDto.getTitle());
        assertEquals(task.getContent(), taskDto.getContent());
    }

    @Test
    void testMapToTaskDto() {

        //Given
        Task task = new Task("title", "content");

        //Then
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //When
        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getTitle(), taskDto.getTitle());
        assertEquals(task.getContent(), taskDto.getContent());
    }

    @Test
    void testMapToTaskDtolist() {

        //Given
        List<Task> taskList = List.of(new Task("title1", "content1"),
                new Task("title2", "content2"),
                new Task("title3", "content3"));

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtolist(taskList);

        //Then
        assertEquals(3, taskDtoList.size());
        assertEquals("title1", taskDtoList.get(0).getTitle());
        assertEquals("title2", taskDtoList.get(1).getTitle());
        assertEquals("title3", taskDtoList.get(2).getTitle());
    }
}