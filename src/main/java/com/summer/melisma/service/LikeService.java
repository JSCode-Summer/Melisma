package com.summer.melisma.service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.summer.melisma.model.dto.LikeDto;
import com.summer.melisma.model.entity.LikeEntity;
import com.summer.melisma.model.repository.LikeRepository;
import com.summer.melisma.model.vo.LikeVo;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserService userService;

    /**
     * <b>Create Related Method.</b>
     * <p>
     * 단일 like를 생성한다.
     * 
     * @param dto
     */
    public void create(LikeDto dto) {
        LikeEntity entity = LikeEntity.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now()).setCreatedBy(userService.getUserId());

        likeRepository.save(entity);
    }

    public LikeVo search(UUID id) {
        Optional<LikeEntity> entityOpt = likeRepository.findById(id);

        if(entityOpt.isPresent()) {
            return LikeVo.toVo(entityOpt.get());
        }else {
            throw new NullPointerException();
        }
    }

    public List<LikeVo> searchList() {
        List<LikeEntity> entities = likeRepository.findAll();
        return entities.stream().map(entity -> LikeVo.toVo(entity)).collect(Collectors.toList());
    }

    public void delete(UUID id) {
        Optional<LikeEntity> entityOpt = likeRepository.findById(id);

        if(entityOpt.isPresent()) {
            likeRepository.delete(entityOpt.get());
        }else {
            throw new NullPointerException();
        }
    }
}
