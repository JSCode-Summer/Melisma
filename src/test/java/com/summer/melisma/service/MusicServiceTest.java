package com.summer.melisma.service;

import com.summer.melisma.model.dto.MusicDto;
import com.summer.melisma.model.vo.MusicVo;
import com.summer.melisma.model.entity.UserEntity;
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
    MusicService musicService;

    @Autowired
    UserRepository userRepository;



    @Test
    public void 생성_테스트() {
        //given
        LocalDateTime now = LocalDateTime.of(2022, 03, 20, 0, 0, 0);
        MusicVo vo = createForTest();

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
        //3개의 음악 저장
        for (int i = 0; i < 3; i++) {
            createForTest();
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

    @Test
    public void 단일_조회_테스트(){
        //given
        MusicVo vo = createForTest();

        //when
        MusicVo findVo = musicService.readOne(vo.getId());

        //then
        System.out.println(">>>>> vo createdBy = " + vo.getCreatedBy());
        System.out.println(">>>>> findVo createdBy = " + findVo.getCreatedBy());
        assertThat(findVo.getCreatedBy()).isEqualTo(vo.getCreatedBy());

    }



    @Test
    public void 삭제_테스트(){
        //given
        MusicVo vo = createForTest();
        UUID id = vo.getId();

        //when
        musicService.delete(id);

        //then
        assertThat(musicService.isEmpty(id));
    }


    public MusicVo createForTest(){
        UserEntity userEntity = userRepository.findByCid(100l).get();
        UUID id = UUID.randomUUID();
        MusicDto musicDto = MusicDto.builder()
                .id(id)
                .musicUrl("")
                .views(0)
                .createdBy(userEntity.getId()).build();

        return musicService.create(musicDto);
    }
}
