package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloCardDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("badges")
    private CreatedTrelloCardBadge badges;

    public CreatedTrelloCardDto(String id, CreatedTrelloCardBadge badges) {
        this.id = id;
        this.badges = badges;
    }

}
