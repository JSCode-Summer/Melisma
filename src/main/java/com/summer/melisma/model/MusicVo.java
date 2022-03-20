package com.summer.melisma.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Builder
@Data
public class MusicVo {

    private final UUID id;

    private final String musicUrl;

    private final Integer views;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    private final UUID createdBy;

    /**
     * get count from table 'likes'
     */
    private final Integer likes;
    
//    public static MusicVo(MusicDto, LikeDto){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicVo musicVo = (MusicVo) o;
        return Objects.equals(id, musicVo.id) &&
                Objects.equals(musicUrl, musicVo.musicUrl) &&
//                Objects.equals(views, musicVo.views) &&
                Objects.equals(createdAt, musicVo.createdAt) &&
                Objects.equals(updatedAt, musicVo.updatedAt) &&
                Objects.equals(createdBy, musicVo.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, musicUrl, views, createdAt, updatedAt, createdBy);
    }
}
