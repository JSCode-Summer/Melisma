package com.summer.melisma.service;

import java.time.LocalDateTime;
import java.util.UUID;

import com.summer.melisma.model.dto.PlaylistDto;
import com.summer.melisma.model.entity.PlaylistEntity;
import com.summer.melisma.model.repository.PlaylistRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    /**
     * <b>Create Related Method.</b>
     * <p>
     * 단일 playlist를 생성한다.
     * 
     * @param dto
     */
    public void create(PlaylistDto dto) {
        PlaylistEntity entity = PlaylistEntity.toEntity(dto);
        
        UUID userId = UUID.randomUUID();    // TODO:: 실제 userId로 변경
        entity.setCreatedAt(LocalDateTime.now()).setCreatedBy(userId);

        playlistRepository.save(entity);
    }
}
