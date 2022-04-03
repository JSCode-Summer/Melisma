package com.summer.melisma.model.dto;

import com.summer.melisma.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Builder
@Data
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID id;

    private String username;

    private String password;

    private String salt;

    public static UserDto toDto(UserEntity entity) {
        UserDto dto = UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .salt(entity.getSalt())
                .build();
        return dto;
    }
}
