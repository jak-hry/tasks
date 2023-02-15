package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;

    @GetMapping
    public List<TaskDto> getTasks() {
        List<Task> taskList = service.getAllTasks();
        return taskMapper.mapToTaskDtolist(taskList);
    }

    @GetMapping("/get/{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) {
        Optional<Task> task = service.findTaskById(taskId);
        if (task.isPresent()) {
            return taskMapper.mapToTaskDto(task.get());
        } else {
            return new TaskDto();
        }
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
        if (!getTasks().remove(taskId)) {
            return "Task with id: \"" + taskId + "\" does not exist";
        } else {
            return "Task with id: \"" + taskId + "\" removed";
        }
    }

    @PutMapping
    public TaskDto updateTask(@RequestBody TaskDto task) {
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping
    public String createTask(@RequestBody TaskDto task) {
        if (!(getTasks().contains(task))) {
            getTasks().add(task);
            return "task with title: \"" + task.getTitle() + "\" added";
        } else {
            return "Task with title: \"" + task.getTitle() + "\" does not exist";
        }
    }
}
