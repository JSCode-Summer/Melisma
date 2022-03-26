package com.summer.melisma.model.repository;

import java.util.Optional;
import java.util.UUID;

import com.summer.melisma.model.entity.CommentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{
    Optional<CommentEntity> findById(UUID id);
}
