package com.summer.melisma.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.summer.melisma.model.dto.CommentDto;
import com.summer.melisma.model.entity.CommentEntity;
import com.summer.melisma.model.repository.CommentRepository;
import com.summer.melisma.model.vo.CommentVo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
@Transactional
public class CommentServiceTest {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Test
    @DisplayName("코멘트 단일 생성")
    void 코멘트_단일생성() {

        UUID commentId = UUID.randomUUID();
        UUID musicId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String content = "TestContent";

        CommentEntity commentEntity = CommentEntity.builder()
            .id(commentId)
            .content(content)
            .musicId(musicId)
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .createdBy(userId)
            .build();

        // when
        commentRepository.save(commentEntity);
        Optional<CommentEntity> commentOpt = commentRepository.findById(commentId);


        // then
        Assertions.assertThat(commentOpt.get().getId()).isEqualTo(commentId);
        Assertions.assertThat(commentOpt.get().getCreatedBy()).isEqualTo(userId);
    }

    @Test
    @DisplayName("코멘트 단일 조회")
    void 코멘트_단일조회() {

        UUID commentId = UUID.randomUUID();
        UUID musicId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String content = "TestContent";

        CommentEntity commentEntity = CommentEntity.builder()
            .id(commentId)
            .content(content)
            .musicId(musicId)
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .createdBy(userId)
            .build();

        // when
        commentRepository.save(commentEntity);
        CommentVo resultVo = commentService.search(commentId);

        Assertions.assertThat(commentEntity.getId()).isEqualTo(resultVo.getId());
    }

    @Test
    @DisplayName("코멘트 단일 삭제")
    void 코멘트_단일삭제() {

        UUID commentId = UUID.randomUUID();
        UUID musicId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String content = "TestContent";

        CommentEntity commentEntity = CommentEntity.builder()
            .id(commentId)
            .content(content)
            .musicId(musicId)
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .createdBy(userId)
            .build();

        commentRepository.save(commentEntity);
        Optional<CommentEntity> commentOpt = commentRepository.findById(commentId);
        Assertions.assertThat(commentOpt.get().getId()).isEqualTo(commentId);
            
        // when
        commentService.delete(commentId);

        // then
        org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class,
        () -> commentService.search(commentId));
    }

    @Test
    @DisplayName("코멘트 단일 수정")
    void 코멘트_단일수정() {

        UUID commentId = UUID.randomUUID();
        UUID musicId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String content = "TestContent";

        CommentEntity commentEntity = CommentEntity.builder()
            .id(commentId)
            .content(content)
            .musicId(musicId)
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .createdBy(userId)
            .build();

        // when
        CommentEntity savedEntity = commentRepository.save(commentEntity);
        
        CommentDto updatedDto = CommentDto.builder()
        .id(savedEntity.getId())
        .content("UpdatedContent")
        .musicId(musicId)
        .createdAt(savedEntity.getCreatedAt())
        .updatedAt(LocalDateTime.now())
        .createdBy(savedEntity.getCreatedBy())
        .build();


        commentService.update(updatedDto);

        // then
        Optional<CommentEntity> commentOpt = commentRepository.findById(commentId);
        Assertions.assertThat(commentOpt.get().getId()).isEqualTo(commentId);

        Assertions.assertThat(commentEntity.getCreatedAt()).isBefore(commentOpt.get().getUpdatedAt());
    }
    
    @Test
    @DisplayName("코멘트 단일 일부 수정")
    void 코멘트_단일일부수정() {

        UUID commentId = UUID.randomUUID();
        UUID musicId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String content = "TestContent";

        CommentEntity commentEntity = CommentEntity.builder()
            .id(commentId)
            .content(content)
            .musicId(musicId)
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .createdBy(userId)
            .build();

        // when
        CommentEntity savedEntity = commentRepository.save(commentEntity);

        CommentDto changedDto = CommentDto.builder()
            .id(savedEntity.getId())
            .content("chagnedContent")
            .createdAt(savedEntity.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .createdBy(savedEntity.getCreatedBy())
            .build();

        commentService.change(changedDto);

        // then
        Optional<CommentEntity> commentOpt = commentRepository.findById(commentId);
        Assertions.assertThat(commentOpt.get().getId()).isEqualTo(commentId);

        Assertions.assertThat(commentEntity.getCreatedAt()).isBefore(commentOpt.get().getUpdatedAt());
    }
}
