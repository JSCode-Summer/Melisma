package com.summer.melisma.service;

import com.summer.melisma.model.MusicEntity;
import com.summer.melisma.model.User;
import com.summer.melisma.repository.MusicRepository;
import com.summer.melisma.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class MusicServiceTest {

    @Autowired
    MusicRepository musicRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void timeStampAnnotationTest() {
        //given
        LocalDateTime now = LocalDateTime.of(2022, 03, 20, 0, 0, 0);

        User user = userRepository.findByCid(100l).get();
        UUID id = UUID.randomUUID();
//        MusicEntity music = new MusicEntity(100, id, "",0, null, null, user.getId());
        MusicEntity music = MusicEntity.builder()
                .cid(100)
                .id(id)
                .musicUrl("")
                .views(0)
                .createdBy(user.getId()).build();
//        music.setCid(1);
//        music.setId(UUID.randomUUID());
        musicRepository.save(music);

        //when
        List<MusicEntity> musics = musicRepository.findAll();

        //then
        MusicEntity firstMusic = musics.get(0);

        System.out.println(">>>>> createDate = " + firstMusic.getCreatedAt() + ", modifiedDate = " + firstMusic.getUpdatedAt());
        assertNotNull(firstMusic.getCreatedAt());
        assertNotNull(firstMusic.getUpdatedAt());
        assertThat(firstMusic.getCreatedAt()).isAfter(now);
        assertThat(firstMusic.getUpdatedAt()).isAfter(now);
    }
}