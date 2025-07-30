package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloValidatorTest {

    private final TrelloValidator trelloValidator = new TrelloValidator();

    @Test
    void shouldFilterTestBoards() {
        //Given
        TrelloBoard board1 = new TrelloBoard("1", "Test", new ArrayList<>());
        TrelloBoard board2 = new TrelloBoard("2", "Test2", new ArrayList<>());
        List<TrelloBoard> boards = Arrays.asList(board1, board2);

        //When
        List<TrelloBoard> filtered = trelloValidator.validateTrelloBoards(boards);

        //Then
        assertEquals(1, filtered.size());
        assertEquals("Test2", filtered.get(0).getName());
    }
}