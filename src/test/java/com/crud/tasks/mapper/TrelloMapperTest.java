package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class TrelloMapperTest {

    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void testMapToBoards() {

        //Given
        List<TrelloBoardDto> trelloBoardDtoList = List.of(new TrelloBoardDto("id", "name",
                List.of(new TrelloListDto("id1", "name1", false))));

        //When
        List<TrelloBoard> result = trelloMapper.mapToBoards(trelloBoardDtoList);
        TrelloList trelloList = result.get(result.size() - 1).getLists().get(0);

        //Then
        assertEquals(1, result.size());
        assertEquals("id1", trelloList.getId());
        assertEquals("name1", trelloList.getName());
        assertFalse(trelloList.isClosed());
    }

    @Test
    public void testMapToBoardsDto() {

        //Given
        List<TrelloBoard> trelloBoardList = List.of(new TrelloBoard("id", "name",
                List.of(new TrelloList("id1", "name1", false))));

        //When
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(trelloBoardList);
        TrelloListDto trelloListDto = result.get(result.size() - 1).getLists().get(0);

        //Then
        assertEquals(1, result.size());
        assertEquals("id1", trelloListDto.getId());
        assertEquals("name1", trelloListDto.getName());
        assertFalse(trelloListDto.isClosed());
    }

    @Test
    public void testMapToList() {

        //Given
        List<TrelloListDto> trelloListDtoList = List.of(new TrelloListDto("id", "name", false));

        //When
        List<TrelloList> result = trelloMapper.mapToList(trelloListDtoList);
        TrelloList trelloList = result.get(result.size() - 1);


        //Then
        assertEquals(1, result.size());
        assertEquals("id", trelloList.getId());
        assertEquals("name", trelloList.getName());
        assertFalse(trelloList.isClosed());
    }

    @Test
    public void testMapToDtoList() {

        //Given
        List<TrelloList> trelloListList = List.of(new TrelloList("id", "name", false));

        //When
        List<TrelloListDto> result = trelloMapper.mapToListDto(trelloListList);
        TrelloListDto trelloListDto = result.get(result.size() - 1);

        //Then
        assertEquals(1, result.size());
        assertEquals("id", trelloListDto.getId());
        assertEquals("name", trelloListDto.getName());
        assertFalse(trelloListDto.isClosed());
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
