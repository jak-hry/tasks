package com.crud.tasks.scheduler;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.trello.service.SimpleMailService;
import com.crud.tasks.trello.config.AdminConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class EmailSchedulerTest {

    @Mock
    private SimpleMailService simpleMailService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void sendInformationEmailWithZeroTasks() {

        //Given && When
        when(taskRepository.count()).thenReturn(0L);
        EmailScheduler emailScheduler = new EmailScheduler(simpleMailService, taskRepository, adminConfig);
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleMailService, Mockito.times(1)).send(Mockito.any(Mail.class));
        verify(taskRepository).count();
        verify(adminConfig).getAdminMail();
        verifyNoMoreInteractions(simpleMailService, taskRepository, adminConfig);
    }

    @Test
    public void sendInformationEmailWithOneTask() {

        //Given && When
        when(taskRepository.count()).thenReturn(1L);
        EmailScheduler emailScheduler = new EmailScheduler(simpleMailService, taskRepository, adminConfig);
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleMailService, Mockito.times(1)).send(Mockito.any(Mail.class));
        verify(taskRepository).count();
        verify(adminConfig).getAdminMail();
        verifyNoMoreInteractions(simpleMailService, taskRepository, adminConfig);
    }

    @Test
    public void sendInformationEmailWithMultipleTasks() {

        //Given && When
        when(taskRepository.count()).thenReturn(5L);
        EmailScheduler emailScheduler = new EmailScheduler(simpleMailService, taskRepository, adminConfig);
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleMailService, Mockito.times(1)).send(Mockito.any(Mail.class));
        verify(taskRepository).count();
        verify(adminConfig).getAdminMail();
        verifyNoMoreInteractions(simpleMailService, taskRepository, adminConfig);
    }
}

