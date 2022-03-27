package com.summer.melisma.service;

import com.summer.melisma.model.MusicDto;
import com.summer.melisma.model.MusicEntity;
import com.summer.melisma.model.MusicVo;
import com.summer.melisma.model.User;
import com.summer.melisma.repository.MusicRepository;
import com.summer.melisma.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
//@Transactional
public class MusicServiceTest {

    @Autowired
    MusicService musicService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void timeStampAnnotationTest() {
        //given
        LocalDateTime now = LocalDateTime.of(2022, 03, 20, 0, 0, 0);

        User user = userRepository.findByCid(100l).get();
        UUID id = UUID.randomUUID();
        MusicDto musicDto = MusicDto.builder()
                .id(id)
                .musicUrl("")
                .views(0)
                .createdBy(user.getId()).build();

        musicService.create(musicDto);

        //when
        List<MusicVo> musics = musicService.readAll();

        //then
        MusicVo firstMusic = musics.get(0);

        System.out.println(">>>>> createDate = " + firstMusic.getCreatedAt() + ", modifiedDate = " + firstMusic.getUpdatedAt());
        assertNotNull(firstMusic.getCreatedAt());
        assertNotNull(firstMusic.getUpdatedAt());
        assertThat(firstMusic.getCreatedAt()).isAfter(now);
        assertThat(firstMusic.getUpdatedAt()).isAfter(now);
    }

    @Test
    public void 전체_조회_테스트() {
        //given
        User user = userRepository.findByCid(100l).get();

        //3개의 음악 저장
        for (int i = 0; i < 3; i++) {
            UUID id = UUID.randomUUID();
            MusicDto musicDto = MusicDto.builder()
                    .id(id)
                    .musicUrl("")
                    .views(0)
                    .createdBy(user.getId()).build();

            musicService.create(musicDto);
        }

        //when
        List<MusicVo> vos = musicService.readAll();


        //then
        for (int i = 0; i < 3; i++) {
            MusicVo vo = vos.get(i);
            System.out.println(">>>>> id = " + vo.getId());
            assertThat(vo).isNotNull();
        }
    }

    public MusicVo createForTest(){
        User user = userRepository.findByCid(100l).get();
        UUID id = UUID.randomUUID();
        MusicDto musicDto = MusicDto.builder()
                .id(id)
                .musicUrl("")
                .views(0)
                .createdBy(user.getId()).build();

        return musicService.create(musicDto);
    }
}
