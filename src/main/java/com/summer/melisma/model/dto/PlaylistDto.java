package com.summer.melisma.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.summer.melisma.model.entity.PlaylistEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Builder
@Getter
@ToString
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDto {
    private UUID id;

    @Setter
    private PlaylistDetailDto playlistDetail;

    // @Setter
    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime updatedAt;

    @Setter
    private UUID createdBy;

    /**
     * <b>Convert Method</b>
     * <p>
     * PlaylistEntity => PlaylistDto
     * 
     * @param dto : PlaylistEntity
     * @return PlaylistDto
     */
    public static PlaylistDto toDto(PlaylistEntity entity) {
        PlaylistDto dto = PlaylistDto.builder()
            .id(entity.getId())
            .playlistDetail(entity.getPlaylistDetail())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .createdBy(entity.getCreatedBy())
            .build();

        return dto;
    }
}
