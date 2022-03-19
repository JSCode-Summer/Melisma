package com.summer.melisma.model.vo;

import java.time.LocalDateTime;
import java.util.UUID;

import com.summer.melisma.model.dto.PlaylistDetailDto;

import lombok.Setter;

public class PlaylistVo {
    private UUID id;

    @Setter
    private PlaylistDetailDto details;

    @Setter
    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime updatedAt;

    @Setter
    private UUID createdBy;
}
