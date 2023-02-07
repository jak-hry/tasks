package com.crud.tasks.domain;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    @GetMapping
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @GetMapping("/get/{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) {
        return new TaskDto(1L, "Test title", "test_content");
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        getTasks().remove(taskId);
    }

    @PutMapping
    public TaskDto updateTask(@RequestBody TaskDto task) {
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping
    public void createTask(@RequestBody TaskDto task) {
        if (!(getTasks().contains(task))) {
            getTasks().add(task);
        }
    }
}
