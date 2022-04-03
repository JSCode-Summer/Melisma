package com.summer.melisma.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.summer.melisma.model.entity.CommentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{
    Optional<CommentEntity> findById(UUID id);
    List<CommentEntity> findByMusicId(UUID musicId);
}
