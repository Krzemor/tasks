package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloCardAttachments {

    @JsonProperty("trello")
    private CreatedTrelloCardTrello trello;

    public CreatedTrelloCardAttachments(CreatedTrelloCardTrello trello) {
        this.trello = trello;
    }

    public CreatedTrelloCardAttachments() {}
}
