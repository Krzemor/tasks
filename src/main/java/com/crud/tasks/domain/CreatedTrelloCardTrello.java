package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloCardTrello {

    @JsonProperty("board")
    private int board;

    @JsonProperty("card")
    private int card;

    public CreatedTrelloCardTrello(int board, int card) {
        this.board = board;
        this.card = card;
    }
}
