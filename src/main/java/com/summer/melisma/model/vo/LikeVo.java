package com.summer.melisma.model.vo;

import java.time.LocalDateTime;
import java.util.UUID;

import com.summer.melisma.model.entity.LikeEntity;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeVo {
    private UUID id;

    @Setter
    private UUID musicId;

    @Setter
    private LocalDateTime createdAt;

    @Setter
    private UUID createdBy;


    public static LikeVo toVo(LikeEntity entity) {
        LikeVo vo = LikeVo.builder()
            .id(entity.getId())
            .musicId(entity.getMusicId())
            .createdAt(entity.getCreatedAt())
            .createdBy(entity.getCreatedBy())
            .build();

        return vo;
    }
}
