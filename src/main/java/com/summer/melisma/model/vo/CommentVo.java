package com.summer.melisma.model.vo;

import java.time.LocalDateTime;
import java.util.UUID;

import com.summer.melisma.model.dto.CommentDto;
import com.summer.melisma.model.entity.CommentEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    private UUID id;

    @Setter 
    private String content;

    @Setter 
    private UUID musicId;

    @Setter
    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime updatedAt;

    @Setter
    private UUID createdBy;

    
    public static CommentVo toVo(CommentEntity entity) {
        CommentVo vo = CommentVo.builder()
            .id(entity.getId())
            .content(entity.getContent())
            .musicId(entity.getMusicId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getCreatedAt())
            .createdBy(entity.getCreatedBy())
            .build();

        return vo;
    }
}
