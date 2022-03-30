package com.summer.melisma.model.users.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.summer.melisma.model.users.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Builder
@Getter
@ToString
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID id;

    @Setter
    private String username;

    @Setter
    private String password;

    @Setter
    private UUID salt;

    @Setter
    private String role;

    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime updatedAt;

    public static UserDto toDto(UserEntity entity) {
        UserDto dto = UserDto.builder()
            .id(entity.getId())
            .username(entity.getUsername())
            .password(entity.getPassword())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .build();

        return dto;
    }
}
