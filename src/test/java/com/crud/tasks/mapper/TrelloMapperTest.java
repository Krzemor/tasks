package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TrelloMapperTest {

    private final TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void mapToBoardsAndBack() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1","ListDto",false);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("11","BoardDto", List.of(trelloListDto));


        //When
        List<TrelloBoard> mappedBoards = trelloMapper.mapToBoards(List.of(trelloBoardDto));
        List<TrelloBoardDto> mappedBack = trelloMapper.mapToBoardsDto(mappedBoards);

        //Then
        assertThat(mappedBoards).hasSize(1);
        assertThat(mappedBoards.get(0).getId()).isEqualTo("11");
        assertThat(mappedBoards.get(0).getName()).isEqualTo("BoardDto");
        assertThat(mappedBoards.get(0).getLists()).hasSize(1);
        assertThat(mappedBoards.get(0).getLists().get(0).getName()).isEqualTo("ListDto");

        assertThat(mappedBack).hasSize(1);
        assertThat(mappedBack.get(0).getId()).isEqualTo("11");
        assertThat(mappedBack.get(0).getName()).isEqualTo("BoardDto");
        assertThat(mappedBack.get(0).getLists()).hasSize(1);
        assertThat(mappedBack.get(0).getLists().get(0).getId()).isEqualTo("1");
    }

    @Test
    public void mapToListAndBack() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1","ListDto",true);

        //When
        List<TrelloList> mappedLists = trelloMapper.mapToList(List.of(trelloListDto));
        List<TrelloListDto> mappedBack = trelloMapper.mapToListDto(mappedLists);

        //Then
        assertThat(mappedLists).hasSize(1);
        assertThat(mappedLists.get(0).getId()).isEqualTo("1");
        assertThat(mappedLists.get(0).getName()).isEqualTo("ListDto");
        assertThat(mappedLists.get(0).isClosed()).isTrue();

        assertThat(mappedBack.get(0).getId()).isEqualTo("1");
        assertThat(mappedBack.get(0).getName()).isEqualTo("ListDto");
        assertThat(mappedBack.get(0).isClosed()).isTrue();
    }

    @Test
    public void mapToCardAndBack() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("CardDto", "Description", "top", "1");

        //When
        TrelloCard mappedCard = trelloMapper.mapToCard(trelloCardDto);
        TrelloCardDto mappedBack = trelloMapper.mapToCardDto(mappedCard);

        //Then
        assertThat(mappedCard.getName()).isEqualTo("CardDto");
        assertThat(mappedCard.getDescription()).isEqualTo("Description");
        assertThat(mappedCard.getPos()).isEqualTo("top");
        assertThat(mappedCard.getListId()).isEqualTo("1");

        assertThat(mappedBack.getName()).isEqualTo("CardDto");
        assertThat(mappedBack.getDescription()).isEqualTo("Description");
        assertThat(mappedBack.getPos()).isEqualTo("top");
        assertThat(mappedBack.getListId()).isEqualTo("1");
    }
}