package com.summer.melisma.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.summer.melisma.model.dto.CommentDto;
import com.summer.melisma.model.entity.CommentEntity;
import com.summer.melisma.model.repository.CommentRepository;
import com.summer.melisma.model.vo.CommentVo;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    /**
     * <b>Create Related Method.</b>
     * <p>
     * 단일 comment를 생성한다.
     * 
     * @param dto
     */
    public void create(CommentDto dto) {
        CommentEntity entity = CommentEntity.toEntity(dto);
        
        UUID userId = UUID.randomUUID();    // TODO:: 실제 userId로 변경
        entity.setCreatedAt(LocalDateTime.now()).setCreatedBy(userId);

        commentRepository.save(entity);
    }

    public List<CommentVo> searchList() {
        return null;
    }

    public CommentVo search(UUID id) {
        Optional<CommentEntity> entityOpt = commentRepository.findById(id);

        if(entityOpt.isPresent()) {
            return CommentVo.toVo(entityOpt.get());
        }else {
            throw new NullPointerException();
        }
    }

    public void delete(UUID id) {
        Optional<CommentEntity> entityOpt = commentRepository.findById(id);

        if(entityOpt.isPresent()) {
            commentRepository.delete(entityOpt.get());
        }else {
            throw new NullPointerException();
        }
    }
}
