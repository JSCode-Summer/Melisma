package com.summer.melisma.repository;

import com.summer.melisma.model.entity.UserEntity;
import com.summer.melisma.model.vo.UserVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

}
