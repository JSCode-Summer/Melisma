package com.summer.melisma.repository;

import com.summer.melisma.model.entity.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MusicRepository extends JpaRepository<MusicEntity, Integer> {
    public void deleteById(UUID uid);
    Optional<MusicEntity> findById(UUID id);
}
