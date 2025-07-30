package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailSchedulerTest {

    private EmailScheduler emailScheduler;
    private TaskRepository taskRepository;
    private SimpleEmailService simpleEmailService;
    private AdminConfig adminConfig;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        simpleEmailService = mock(SimpleEmailService.class);
        adminConfig = mock(AdminConfig.class);

        emailScheduler = new EmailScheduler(simpleEmailService, taskRepository, adminConfig);
    }

    @Test
    void sholdSendEmailWithCorrectCount() {
        //Given
        when(taskRepository.count()).thenReturn(7L);
        when(adminConfig.getAdminMail()).thenReturn("admin@example.com");

        ArgumentCaptor<Mail> mailCaptor = ArgumentCaptor.forClass(Mail.class);

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService, times(1)).send(mailCaptor.capture());
        Mail sentMail = mailCaptor.getValue();
        assertEquals("admin@example.com", sentMail.getMailTo());
        assertEquals("Tasks: Once a day email", sentMail.getSubject());
        assertEquals("Currently in database you got: 7 tasks", sentMail.getMessage());
    }

    @Test
    void sholdSendEmailWithCorrectCountSingle() {
        //Given
        when(taskRepository.count()).thenReturn(1L);
        when(adminConfig.getAdminMail()).thenReturn("admin@example.com");

        ArgumentCaptor<Mail> mailCaptor = ArgumentCaptor.forClass(Mail.class);

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService, times(1)).send(mailCaptor.capture());
        Mail sentMail = mailCaptor.getValue();
        assertEquals("Currently in database you got: 1 task", sentMail.getMessage());
    }
}