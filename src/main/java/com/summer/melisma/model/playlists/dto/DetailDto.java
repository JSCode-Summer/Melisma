package com.summer.melisma.model.playlists.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class DetailDto {
    // TODO :: 필드 추가
    private UUID id;
    private UUID musicId;
}
