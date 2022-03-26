package com.summer.melisma.service;

import java.time.LocalDateTime;
import java.util.UUID;

import com.summer.melisma.model.dto.LikeDto;
import com.summer.melisma.model.entity.LikeEntity;
import com.summer.melisma.model.repository.LikeRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    /**
     * <b>Create Related Method.</b>
     * <p>
     * 단일 like를 생성한다.
     * 
     * @param dto
     */
    public void create(LikeDto dto) {
        LikeEntity entity = LikeEntity.toEntity(dto);
        
        UUID userId = UUID.randomUUID();    // TODO:: 실제 userId로 변경
        entity.setCreatedAt(LocalDateTime.now()).setCreatedBy(userId);

        likeRepository.save(entity);
    }
}
