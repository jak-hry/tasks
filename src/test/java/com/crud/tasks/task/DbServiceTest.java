package com.crud.tasks.task;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.trello.service.DbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DbServiceTest {

    @Autowired
    private DbService dbService;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void shouldSaveTask() throws TaskNotFoundException {

        //Given
        Task task = new Task("title", "content");

        //When
        dbService.saveTask(task);
        Task expectedTask = dbService.findTaskById(task.getId());

        //Then
        assertEquals(expectedTask.getId(), task.getId());
        assertEquals("title", expectedTask.getTitle());
        assertEquals("content", expectedTask.getContent());

        //CleanUp
        taskRepository.deleteById(task.getId());
    }

    @Test
    void shouldRemoveTask() {

        //Given
        Task task = new Task("title", "content");

        //When && Then
        dbService.saveTask(task);
        assertTrue(taskRepository.existsById(task.getId()));
        dbService.removeTask(task.getId());
        assertFalse(taskRepository.existsById(task.getId()));
    }

    @Test
    void shouldGetAllTasks() {

        //Given
        Task task1 = new Task("title1", "content1");
        Task task2 = new Task("title2", "content2");
        Task task3 = new Task("title3", "content3");

        //When
        taskRepository.saveAll(List.of(task1, task2, task3));
        List<Task> taskList = dbService.getAllTasks();

        //Then
        assertEquals(3, taskList.size());

        //CleanUp
        taskRepository.deleteById(task1.getId());
        taskRepository.deleteById(task2.getId());
        taskRepository.deleteById(task3.getId());
    }

    @Test
    void shouldRenameTitleAndContentTask() {

        //Given
        Task task = new Task("title", "content");

        //When
        dbService.saveTask(task);
        task.setTitle("correctTitle");
        task.setContent("correctContent");
        dbService.saveTask(task);

        //Then
        assertEquals("correctTitle", task.getTitle());
        assertEquals("correctContent", task.getContent());

        //CleanUp
        taskRepository.deleteById(task.getId());
    }
}
