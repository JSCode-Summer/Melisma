package com.summer.melisma.model.entity;

import com.summer.melisma.model.dto.MusicDto;
import com.summer.melisma.model.dto.UserDto;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "users")
@Entity
@Data
@Builder
public class UserEntity extends TimeStampEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Long cid;

    @Type(type = "uuid-char")
    @Column(name = "id")
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    //int? Integer?
    @Column(name = "salt")
    private String salt;


    public static UserEntity toEntity(UserDto dto) {
        UserEntity entity = UserEntity.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .salt(dto.getSalt())
                .build();

        return entity;
    }
}
