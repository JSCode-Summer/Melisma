package com.summer.melisma.model.dto;

import com.summer.melisma.model.entity.LikeEntity;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Builder
@Getter
@ToString
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class LikeDto {
    private UUID id;

    @Setter
    private UUID musicId;

    @Setter
    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime updatedAt;

    @Setter
    private UUID createdBy;

    public static LikeDto toEntity(LikeEntity entity) {
        LikeDto dto = LikeDto.builder()
            .id(entity.getId())
            .musicId(entity.getMusicId())
            .createdAt(entity.getCreatedAt())
            .createdBy(entity.getCreatedBy())
            .build();

        return dto;
    }
}
