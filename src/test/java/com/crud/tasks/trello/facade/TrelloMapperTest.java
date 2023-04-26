package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {

        //Given
        List<TrelloBoardDto> trelloBoardDtoList = List.of(new TrelloBoardDto("id", "name",
                List.of(new TrelloListDto("id1", "name1", false))));

        //When
        List<TrelloBoard> result = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals(1, result.size());
        assertEquals("id1", result.get(result.size()-1).getLists().get(0).getId());
        assertEquals("name1", result.get(result.size()-1).getLists().get(0).getName());
        assertFalse(result.get(result.size()-1).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToBoardsDto() {

        //Given
        List<TrelloBoard> trelloBoardList = List.of(new TrelloBoard("id", "name",
                List.of(new TrelloList("id1", "name1", false))));

        //When
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertEquals(1, result.size());
        assertEquals("id1", result.get(result.size()-1).getLists().get(0).getId());
        assertEquals("name1", result.get(result.size()-1).getLists().get(0).getName());
        assertFalse(result.get(result.size()-1).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToList() {

        //Given
        List<TrelloListDto> trelloListDtoList = List.of(new TrelloListDto("id", "name", false));

        //When
        List<TrelloList> result = trelloMapper.mapToList(trelloListDtoList);

        //Then
        assertEquals(1, result.size());
        assertEquals("id", result.get(result.size()-1).getId());
        assertEquals("name", result.get(result.size()-1).getName());
        assertFalse(result.get(result.size()-1).isClosed());
    }

    @Test
    public void testMapToDtoList() {

        //Given
        List<TrelloList> trelloListList = List.of(new TrelloList("id", "name", false));

        //When
        List<TrelloListDto> result = trelloMapper.mapToListDto(trelloListList);

        //Then
        assertEquals(1, result.size());
        assertEquals("id", result.get(result.size()-1).getId());
        assertEquals("name", result.get(result.size()-1).getName());
        assertFalse(result.get(result.size()-1).isClosed());
    }

    @Test
    public void testMapToCardDto() {

        //Given
        TrelloCard trelloCard = new TrelloCard("name", "desc", "pos", "listId");

        //When
        TrelloCardDto result = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("name", result.getName());
        assertEquals("desc", result.getDescription());
        assertEquals("pos", result.getPos());
        assertEquals("listId", result.getListId());
    }

    @Test
    public void testMapToCard() {

        //Given
        TrelloCardDto trelloCard = new TrelloCardDto("name", "desc", "pos", "listId");

        //When
        TrelloCard result = trelloMapper.mapToCard(trelloCard);

        //Then
        assertEquals("name", result.getName());
        assertEquals("desc", result.getDescription());
        assertEquals("pos", result.getPos());
        assertEquals("listId", result.getListId());
    }
}
