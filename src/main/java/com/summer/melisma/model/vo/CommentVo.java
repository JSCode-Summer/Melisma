package com.summer.melisma.model.vo;

import java.time.LocalDateTime;
import java.util.UUID;


import lombok.Setter;

public class CommentVo {
    private UUID id;

    @Setter 
    private UUID musicId;

    @Setter
    private LocalDateTime createdAt;

    @Setter
    private UUID createdBy;
}
