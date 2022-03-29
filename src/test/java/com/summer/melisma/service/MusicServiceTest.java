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
        List<MusicVo> musics = musicService.searchList();

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
        List<MusicVo> vos = musicService.searchList();


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
        MusicVo findVo = musicService.search(vo.getId());

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

    @Test
    public void 일부_수정_테스트(){
        //given
        MusicVo vo = createForTest();
        UUID id = vo.getId();

        MusicDto dto = MusicDto.builder()
                .musicUrl("url").build();
        //when
        musicService.change(id, dto);

        //then
        assertThat(musicService.search(id).getMusicUrl()).isEqualTo("url");
    }

    @Test
    public void 전체_수정_테스트(){
        //given
        String testStr = "new url";
        int testInt = 200;
        MusicVo vo = createForTest();
        UUID id = vo.getId();

        MusicDto musicDto = MusicDto.builder()
                .id(id)
                .musicUrl(testStr)
                .views(testInt)
                .createdBy(userRepository.findByCid(100l).get().getId()).build();

        //when
        MusicVo updatedMusic = musicService.update(musicDto);

        //then
        assertThat(musicService.search(id).getMusicUrl()).isEqualTo(updatedMusic.getMusicUrl());
        assertThat(musicService.search(id).getViews()).isEqualTo(updatedMusic.getViews());

    }


    public MusicVo createForTest(){
        UserEntity userEntity = userRepository.findByCid(100l).get();
        UUID id = UUID.randomUUID();
        MusicDto musicDto = MusicDto.builder()
                .id(id)
                .musicUrl("")
                .views(10)
                .createdBy(userEntity.getId()).build();

        return musicService.create(musicDto);
    }
}
