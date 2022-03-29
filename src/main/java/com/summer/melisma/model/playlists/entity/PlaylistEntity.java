package com.summer.melisma.model.playlists.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.summer.melisma.model.playlists.dto.PlaylistDetailDto;
import com.summer.melisma.model.playlists.dto.PlaylistDto;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

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
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Table(name = "playlists")
public class PlaylistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Integer cid;

    @Type(type = "uuid-char")
    @Column(name = "id")
    private UUID id;

    @Setter
    @Type(type = "json")
    @Column(name = "playlist", columnDefinition = "json")
    private PlaylistDetailDto playlistDetail;

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

    /**
     * <b>Convert Method</b>
     * <p>
     * PlaylistDto => PlaylistEntity
     * 
     * @param dto : PlaylistDto
     * @return PlaylistEntity
     */
    public static PlaylistEntity toEntity(PlaylistDto dto) {
        PlaylistEntity entity = PlaylistEntity.builder()
            .id(dto.getId())
            .playlistDetail(dto.getPlaylistDetail())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())
            .createdBy(dto.getCreatedBy())
            .build();

        return entity;
    }
}
