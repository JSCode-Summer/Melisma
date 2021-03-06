package com.summer.melisma.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.summer.melisma.model.dto.CommentDto;
import com.summer.melisma.model.entity.CommentEntity;
import com.summer.melisma.model.vo.CommentVo;
import com.summer.melisma.repository.CommentRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userSerivce;

    /**
     * <b>Create Related Method.</b>
     * <p>
     * 단일 comment를 생성한다.
     * 
     * @param dto
     */
    public void create(CommentDto dto) {
        CommentEntity entity = CommentEntity.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now()).setCreatedBy(userSerivce.getUserId());

        commentRepository.save(entity);
    }

    
    public List<CommentVo> searchListByMusicId(UUID musicId) {
        List<CommentEntity> entities = commentRepository.findByMusicId(musicId);

        if(entities.isEmpty()) {
            return entities.stream().map(entity -> CommentVo.toVo(entity)).collect(Collectors.toList());
        }else {
            throw new NullPointerException();
        }
    }
    public List<CommentVo> searchList() {
        List<CommentEntity> entities = commentRepository.findAll();
        return entities.stream().map(entity -> CommentVo.toVo(entity)).collect(Collectors.toList());
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


    public CommentEntity update( CommentDto dto) {
        CommentEntity entity = commentRepository.findById(dto.getId()).get();
        
        entity.setContent(dto.getContent()).setUpdatedAt(LocalDateTime.now());

        commentRepository.save(entity);
        return entity;
    }


    public CommentEntity change(CommentDto dto) {
        CommentEntity entity = commentRepository.findById(dto.getId()).get();
        
        if(dto.getContent()!=null){
            entity.setContent(dto.getContent());
        }
        if(dto.getMusicId()!=null){
            entity.setMusicId(dto.getMusicId());
        }
        entity.setUpdatedAt(LocalDateTime.now());

        commentRepository.save(entity);
        return entity;
    }


}
