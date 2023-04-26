package com.crud.tasks.trello.client;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrelloFacadeTest {

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloMapper trelloMapper;

    @Mock
    private TrelloValidator trelloValidator;

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Test
    public void testFetchTrelloBoards() {
        // given
        List<TrelloBoard> boards = List.of(new TrelloBoard("id1", "board1",
                List.of(new TrelloList("id1", "name1", false))));
        List<TrelloBoardDto> boardDto = List.of(new TrelloBoardDto("id1", "board1",
                List.of(new TrelloListDto("id1", "name1", false))));

        when(trelloService.fetchTrelloBoards()).thenReturn(boardDto);
        when(trelloMapper.mapToBoards(anyList())).thenReturn(boards);
        when(trelloValidator.validateTrelloBoards(anyList())).thenReturn(boards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(boardDto);

        // when
        List<TrelloBoardDto> fetchedBoards = trelloFacade.fetchTrelloBoards();

        // then
        assertEquals(boardDto, fetchedBoards);
        verify(trelloService, times(1)).fetchTrelloBoards();
        verify(trelloMapper, times(1)).mapToBoards(boardDto);
        verify(trelloValidator, times(1)).validateTrelloBoards(boards);
        verify(trelloMapper, times(1)).mapToBoardsDto(boards);
    }

    @Test
    public void testCreateCard() {
        // given
        TrelloCardDto cardDto = new TrelloCardDto("card1", "desc1", "pos1", "list1");
        TrelloCard card = new TrelloCard("card1", "desc1", "pos1", "list1");

        when(trelloMapper.mapToCard(cardDto)).thenReturn(card);
        doNothing().when(trelloValidator).validateCard(card);

        CreatedTrelloCardDto createdCardDto = new CreatedTrelloCardDto("id1", "card1", "url1");
        when(trelloService.createdTrelloCard(trelloMapper.mapToCardDto(card))).thenReturn(createdCardDto);

        // when
        CreatedTrelloCardDto createdCard = trelloFacade.createCard(cardDto);

        // then
        assertEquals(createdCardDto, createdCard);
        verify(trelloMapper, times(1)).mapToCard(cardDto);
        verify(trelloValidator, times(1)).validateCard(card);
        verify(trelloService, times(1)).createdTrelloCard(trelloMapper.mapToCardDto(card));
    }
}
