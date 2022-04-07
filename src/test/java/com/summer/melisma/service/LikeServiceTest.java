package com.summer.melisma.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.summer.melisma.model.entity.LikeEntity;
import com.summer.melisma.repository.LikeRepository;
import com.summer.melisma.model.vo.LikeVo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
@Transactional
public class LikeServiceTest {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private LikeService likeService;


    @Test
    @DisplayName("like생성")
    void like_단일생성() {

        UUID likeId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        LikeEntity likeEntity = LikeEntity.builder()
            .id(likeId)
            .createdAt(LocalDateTime.now())
            .createdBy(userId)
            .build();

        // when
        likeRepository.save(likeEntity);
        Optional<LikeEntity> likeOpt = likeRepository.findById(likeId);


        // then
        Assertions.assertThat(likeOpt.get().getId()).isEqualTo(likeId);
        Assertions.assertThat(likeOpt.get().getCreatedBy()).isEqualTo(userId);
    }

    @Test
    @DisplayName("라이크 단일 조회")
    void 라이크_단일조회() {
        UUID likeId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        LikeEntity likeEntity = LikeEntity.builder()
            .id(likeId)
            .createdAt(LocalDateTime.now())
            .createdBy(userId)
            .build();

        // when
        likeRepository.save(likeEntity);
        LikeVo resultVo = likeService.search(likeId);
        
        // then
        Assertions.assertThat(likeEntity.getId()).isEqualTo(resultVo.getId());
    }

    @Test
    @DisplayName("라이크 단일 삭제")
    void 라이크_단일삭제() {
        UUID likeId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        LikeEntity likeEntity = LikeEntity.builder()
            .id(likeId)
            .createdAt(LocalDateTime.now())
            .createdBy(userId)
            .build();

        likeRepository.save(likeEntity);
        Optional<LikeEntity> likeOpt =likeRepository.findById(likeId);
        Assertions.assertThat(likeOpt.get().getId()).isEqualTo(likeId);
            
        // when
        likeService.delete(likeId);

        // then
        org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class,
        () -> likeService.search(likeId));
    }

}
