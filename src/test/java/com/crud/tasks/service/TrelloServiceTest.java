package com.crud.tasks.service;

import com.crud.tasks.client.TrelloClient;
import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardBadge;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloCardDto;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrelloServiceTest {

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @InjectMocks
    private TrelloService trelloService;

    public TrelloServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCardAndSendEmail() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "description", "top", "list1");
        CreatedTrelloCardBadge badge = new CreatedTrelloCardBadge(3, null);
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", badge);

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        when(adminConfig.getAdminMail()).thenReturn("test@test.com");

        //When
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals("1", result.getId());
        assertEquals(3, result.getBadges().getVotes());

        ArgumentCaptor<Mail> mailCaptor = ArgumentCaptor.forClass(Mail.class);
        verify(emailService, times(1)).send(mailCaptor.capture());
        Mail sentMail = mailCaptor.getValue();

        assertEquals("test@test.com", sentMail.getMailTo());
        assertEquals("Tasks: New Trello Card", sentMail.getSubject());
        assertTrue(sentMail.getMessage().contains("test"));
    }

    @Test
    void shouldNotSendEmailWhenCardNull() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Null Card", "desc", "pos", "listId");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(null);

        //When
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertNull(result);
        verify(emailService, never()).send(any());
    }

}