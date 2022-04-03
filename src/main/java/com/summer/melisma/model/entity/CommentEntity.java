package com.summer.melisma.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.summer.melisma.model.dto.CommentDto;

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
// @ToString(exclude = {"createdBy"})
@ToString
@Accessors(chain = true)
@Table(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Integer cid;

    @Type(type = "uuid-char")
    @Column(name = "id")
    private UUID id;


    @Setter
    @Type(type ="text")
    @Column(name="content",columnDefinition = "TEXT")
    private String content;


    @Setter
    @Type(type = "uuid-char")
    @Column(name = "music_id")
    private UUID musicId;

    @Setter
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Setter
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    
    @Setter
    // @ManyToOne
    @Type(type = "uuid-char")
    @Column(name = "created_by")
    private UUID createdBy;

    public static CommentEntity toEntity(CommentDto dto) {
        CommentEntity entity = CommentEntity.builder()
            .id(dto.getId())
            .content(dto.getContent())
            .musicId(dto.getMusicId())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())
            .createdBy(dto.getCreatedBy())
            .build();

        return entity;
    }
}
