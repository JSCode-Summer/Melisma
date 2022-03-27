package com.summer.melisma.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.summer.melisma.model.users.dto.LoginReqUserDto;
import com.summer.melisma.model.users.dto.UserDto;
import com.summer.melisma.model.users.entity.UserEntity;
import com.summer.melisma.model.users.repository.UserRepository;

// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    // private final PasswordEncoder passwordEncoder;

    public void create(LoginReqUserDto reqUserDto) {
        // 1. 넘어온 LoginReqUserDto로 UserDto 생성. (이때 password + salt값으로 password데이터 생성) 
        // 2. 넘어온 UserDto의 username와 동일한 이름을 가진 유저 조회 -> UQ설정을 해놓았기 때문에 예외처리만 해주기
        // 3. 유저 생성

        UUID userId = UUID.randomUUID();
        UUID salt = UUID.randomUUID();

        String saltedPassword = reqUserDto.getPassword() + salt;
        // String newPassword = passwordEncoder.encode(saltedPassword);

        UserDto dto = UserDto.builder()
            .id(userId)
            .username(reqUserDto.getUsername())
            .password(saltedPassword)
            .salt(salt)
            .createdAt(LocalDateTime.now())
            .build();
        
        UserEntity entity = UserEntity.toEntity(dto);
        userRepository.save(entity);
    }

    public UUID login(LoginReqUserDto reqUserDto) {
        // 1. reqUserDto로 넘어온 username과 대응되는 데이터가 있는지 확인
        // 2. 비밀번호 확인
        Optional<UserEntity> entityOpt = userRepository.findByUsername(reqUserDto.getUsername());

        if(entityOpt.isPresent()) {
            UserEntity storedEntity = entityOpt.get();
            UUID salt = storedEntity.getSalt();
            String loginPassword = reqUserDto.getPassword() + salt;

            if(storedEntity.getPassword().equals(loginPassword)) {
                return storedEntity.getId();
            }
        }
        throw new NullPointerException();
    }
}
