package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloCardBadge {

    @JsonProperty("votes")
    private int votes;

    @JsonProperty("attachmentsByType")
    private CreatedTrelloCardAttachments attachmentsByType;

    public CreatedTrelloCardBadge(int votes, CreatedTrelloCardAttachments attachmentsByType) {
        this.votes = votes;
        this.attachmentsByType = attachmentsByType;
    }
}
