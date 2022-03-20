package com.summer.melisma.model;


import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Entity
@Data
public class MusicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Integer cid;

    @Type(type = "uuid-char")
    @Column(name = "id")
    private UUID id;

    @Column(name = "music_url")
    private String musicUrl;

    //int? Integer?
    @Column(name = "views")
    private Integer views;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Type(type = "uuid-char")
    @Column(name = "created_by")
    private UUID createdBy;


    public static MusicEntity toEntity(MusicDto dto) {
        MusicEntity entity = MusicEntity.builder()
                .id(dto.getId())
                .musicUrl(dto.getMusicUrl())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .createdBy(dto.getCreatedBy())
                .build();

        return entity;
    }
}
