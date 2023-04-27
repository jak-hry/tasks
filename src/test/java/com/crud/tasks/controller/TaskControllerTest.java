package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DbService service;
    @MockBean
    private TaskMapper taskMapper;

    @Test
    void shouldGetAllTasks() throws Exception {

        // Given
        List<Task> taskList = List.of(new Task(1L, "Test Title", "Test Content"));
        List<TaskDto> taskListDto = List.of(new TaskDto(1L, "Test Title", "Test Content"));

        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtolist(any())).thenReturn(taskListDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Test Title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("Test Content")));
    }

    @Test
    void shouldGetTask() throws Exception {

        // Given
        Task task = new Task(1L, "Test Title", "Test Content");
        TaskDto taskDto = new TaskDto(1L, "Test Title", "Test Content");

        when(service.findTaskById(any())).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test Title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Test Content")));
    }

    @Test
    void shouldCreateTask() throws Exception {

        // Given
        Task task = new Task(1L, "Test Title", "Test Content");
        TaskDto taskDto = new TaskDto(1L, "Test Title", "Test Content");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test Title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Test Content")));
    }

    @Test
    void shouldUpdateTask() throws Exception {

        // Given
        Task task = new Task(1L, "Updated Title", "Updated Content");
        TaskDto taskDto = new TaskDto(1L, "Updated Title", "Updated Content");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Updated Title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Updated Content")));
    }

    @Test
    void shouldDeleteTask() throws Exception {

        // Given
        Long taskId = 1L;

        when(service.findTaskById(taskId)).thenReturn(new Task());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
