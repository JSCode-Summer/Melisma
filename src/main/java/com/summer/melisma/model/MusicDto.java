package com.summer.melisma.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class MusicDto {

    private UUID id;

    private String musicUrl;

    private Integer views;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private UUID createdBy;


    public static MusicDto toDto(MusicEntity entity) {
        MusicDto dto = MusicDto.builder()
                .id(entity.getId())
                .musicUrl(entity.getMusicUrl())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .createdBy(entity.getCreatedBy())
                .build();

        return dto;
    }
}
