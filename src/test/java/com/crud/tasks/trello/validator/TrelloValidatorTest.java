package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloValidatorTest {

    private TrelloValidator trelloValidator = new TrelloValidator();

    @Test
    void shouldFilterOutTestBoards() {

        //Given
        List<TrelloBoard> trelloBoardList = List.of(new TrelloBoard("id1", "name1", new ArrayList<>()),
                new TrelloBoard("id2", "name2", new ArrayList<>()),
                new TrelloBoard("test", "test", new ArrayList<>()));

        //When
        List<TrelloBoard> filteredBoardList = trelloValidator.validateTrelloBoards(trelloBoardList);

        //Then
        assertEquals(2, filteredBoardList.size());
        assertEquals("id1", filteredBoardList.get(0).getId());
        assertEquals("name1", filteredBoardList.get(0).getName());
        assertEquals("id2", filteredBoardList.get(1).getId());
        assertEquals("name2", filteredBoardList.get(1).getName());
    }
}
