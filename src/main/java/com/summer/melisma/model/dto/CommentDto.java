package com.summer.melisma.model.dto;

import com.summer.melisma.model.entity.CommentEntity;

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
public class CommentDto {
    private UUID id;

    @Setter
    private StringBuilder content;

    @Setter 
    private UUID musicId;

    @Setter
    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime updatedAt;

    @Setter
    private UUID createdBy;

    public static CommentDto toEntity(CommentEntity entity) {
        CommentDto dto = CommentDto.builder()
            .id(entity.getId())
            .content(entity.getContent())
            .musicId(entity.getMusicId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .createdBy(entity.getCreatedBy())
            .build();

        return dto;
    }
}
