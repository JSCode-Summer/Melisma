package com.summer.melisma.model.vo;

import com.summer.melisma.model.dto.UserDto;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Builder
@Data
public class UserVo {
    private final UUID id;

    private final String username;

    private final String password;

    private final UUID salt;

    public static UserVo toVo(UserDto dto) {
        UserVo toVo = UserVo.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .salt(dto.getSalt())
                .build();

        return toVo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVo userVo = (UserVo) o;
        return Objects.equals(id, userVo.id) && Objects.equals(password, userVo.password) && Objects.equals(salt, userVo.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, salt);
    }

}
