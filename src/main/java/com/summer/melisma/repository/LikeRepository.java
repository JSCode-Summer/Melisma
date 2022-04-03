package com.summer.melisma.repository;

import java.util.Optional;
import java.util.UUID;

import com.summer.melisma.model.entity.LikeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Integer>{
    Optional<LikeEntity> findById(UUID id);
}
