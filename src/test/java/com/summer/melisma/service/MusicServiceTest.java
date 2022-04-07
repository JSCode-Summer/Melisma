package com.summer.melisma.service;

import com.summer.melisma.model.dto.LoginReqUserDto;
import com.summer.melisma.model.dto.MusicDto;
import com.summer.melisma.model.entity.UserEntity;
import com.summer.melisma.model.vo.MusicVo;
import com.summer.melisma.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void 유저_생성_및_인증(){
        String username = "users";
        String password = "password";
        LoginReqUserDto dto = LoginReqUserDto.builder().username(username).password(password).build();

        userService.create(dto);

        UserEntity user = userRepository.findByUsername(username).get();
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));

    }

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
                .id(id)
                .musicUrl("url")
                .views(100).build();
        //when
        musicService.change(dto);

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
                .createdBy(UUID.randomUUID()).build();

        //when
        musicService.update(musicDto);

        //then
        assertThat(musicService.search(id).getMusicUrl()).isEqualTo(testStr);
        assertThat(musicService.search(id).getViews()).isEqualTo(testInt);

    }


    public MusicVo createForTest(){
        UUID id = UUID.randomUUID();
        MusicDto musicDto = MusicDto.builder()
                .id(id)
                .musicUrl("testStr")
                .views(10)
                .createdBy(UUID.randomUUID()).build();

        return musicService.create(musicDto);
    }
}
