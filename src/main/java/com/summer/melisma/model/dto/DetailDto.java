package com.summer.melisma.model.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class DetailDto {
    // TODO :: νλ μΆκ°
    private UUID id;
    @Setter
    private UUID musicId;
}
