package com.summer.melisma.model;


import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="musics")
public class MusicEntity extends TimeStampEntity{

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

//    @CreatedDate
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;

    @CreatedBy
    @Type(type = "uuid-char")
    @Column(name = "created_by")
    private UUID createdBy;



    public static MusicEntity toEntity(MusicDto dto) {
        MusicEntity entity = MusicEntity.builder()
                .id(dto.getId())
                .musicUrl(dto.getMusicUrl())
//                .createdAt(dto.getCreatedAt())
//                .updatedAt(dto.getUpdatedAt())
                .createdBy(dto.getCreatedBy())
                .build();

        return entity;
    }
}
