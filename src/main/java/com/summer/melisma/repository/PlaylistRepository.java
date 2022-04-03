package com.summer.melisma.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.summer.melisma.model.entity.PlaylistEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Integer>{
    Optional<PlaylistEntity> findById(UUID id);

    List<PlaylistEntity> findByCreatedBy(UUID createdBy);
}
