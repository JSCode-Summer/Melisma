package com.summer.melisma.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.summer.melisma.model.dto.LikeDto;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Accessors(chain = true)
@Table(name = "likes")
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Integer cid;

    @Type(type = "uuid-char")
    @Column(name = "id")
    private UUID id;

    @Setter
    @Type(type = "uuid-char")
    @Column(name = "music_id")
    private UUID musicId;

    @Setter
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Setter
    // @ManyToOne
    @Type(type = "uuid-char")
    @Column(name = "created_by")
    private UUID createdBy;

    public static LikeEntity toEntity(LikeDto dto) {
        LikeEntity entity = LikeEntity.builder()
            .id(dto.getId())
            .musicId(dto.getMusicId())
            .createdAt(dto.getCreatedAt())
            .createdBy(dto.getCreatedBy())
            .build();

        return entity;
    }
}
