package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardBadge;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrelloControllerTest {

    private TrelloController trelloController;
    private TrelloFacade trelloFacade;

    @BeforeEach
    void setUp() {
        trelloFacade = mock(TrelloFacade.class);
        trelloController = new TrelloController(trelloFacade);
    }

    @Test
    void shouldFetchTrelloBoards() {
        //Given
        List<TrelloBoardDto> boardsDtos = List.of(new TrelloBoardDto("1", "test", List.of()));

        when(trelloFacade.fetchTrelloBoards()).thenReturn(boardsDtos);

        //When
        ResponseEntity<List<TrelloBoardDto>> response = trelloController.getTrelloBoards();

        //Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("test", response.getBody().get(0).getName());
    }


}