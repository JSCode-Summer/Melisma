package com.summer.melisma.repository;

import com.summer.melisma.model.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<MusicEntity, Integer> {
import java.util.Optional;
import java.util.UUID;

    Optional<MusicEntity> findById(UUID id);
}
