package com.crud.tasks.scheduler;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.trello.config.AdminConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest
public class EmailSchedulerTest {

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void sendInformationEmailWithZeroTasks() {

        //Given && When
        Mockito.when(taskRepository.count()).thenReturn(0L);
        EmailScheduler emailScheduler = new EmailScheduler(simpleEmailService, taskRepository, adminConfig);
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService, Mockito.times(1)).send(Mockito.any(Mail.class));
        verify(taskRepository).count();
        verify(adminConfig).getAdminMail();
        verifyNoMoreInteractions(simpleEmailService, taskRepository, adminConfig);
    }

    @Test
    public void sendInformationEmailWithOneTask() {

        //Given && When
        Mockito.when(taskRepository.count()).thenReturn(1L);
        EmailScheduler emailScheduler = new EmailScheduler(simpleEmailService, taskRepository, adminConfig);
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService, Mockito.times(1)).send(Mockito.any(Mail.class));
        verify(taskRepository).count();
        verify(adminConfig).getAdminMail();
        verifyNoMoreInteractions(simpleEmailService, taskRepository, adminConfig);
    }

    @Test
    public void sendInformationEmailWithMultipleTasks() {

        //Given && When
        Mockito.when(taskRepository.count()).thenReturn(5L);
        EmailScheduler emailScheduler = new EmailScheduler(simpleEmailService, taskRepository, adminConfig);
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService, Mockito.times(1)).send(Mockito.any(Mail.class));
        verify(taskRepository).count();
        verify(adminConfig).getAdminMail();
        verifyNoMoreInteractions(simpleEmailService, taskRepository, adminConfig);
    }
}

