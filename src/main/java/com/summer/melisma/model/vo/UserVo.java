package com.summer.melisma.model.vo;

import com.summer.melisma.model.dto.UserDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Builder
@Data
public class UserVo{
    private final UUID id;

    private final String username;

    private final String password;

    private final String salt;

    public static UserVo toVo(UserDto dto) {
        UserVo toVo = UserVo.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .salt(dto.getSalt())
                .build();

        return toVo;
    }
}
