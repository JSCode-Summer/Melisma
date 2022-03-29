package com.summer.melisma.model.playlists.vo;

import java.time.LocalDateTime;
import java.util.UUID;

import com.summer.melisma.model.playlists.dto.PlaylistDetailDto;
import com.summer.melisma.model.playlists.entity.PlaylistEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Accessors(chain=true)
public class PlaylistVo {
    private UUID id;

    @Setter
    private PlaylistDetailDto playlistDetail;

    @Setter
    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime updatedAt;

    @Setter
    private UUID createdBy;

    /**
     * <b>Convert Method</b>
     * <p>
     * PlaylistEntity => PlaylistVo
     * 
     * @param entity : PlaylistEntity
     * @return PlaylistVo
     */
    public static PlaylistVo toVo(PlaylistEntity entity) {
        PlaylistVo vo = PlaylistVo.builder()
            .id(entity.getId())
            .playlistDetail(entity.getPlaylistDetail())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .createdBy(entity.getCreatedBy())
            .build();

        return vo;
    }
}
