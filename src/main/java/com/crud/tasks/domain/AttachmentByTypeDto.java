package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttachmentByTypeDto {

    @JsonProperty("trello")
    private Trello trello;
}
