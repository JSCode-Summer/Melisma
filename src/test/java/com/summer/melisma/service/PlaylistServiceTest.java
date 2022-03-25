package com.summer.melisma.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.summer.melisma.model.dto.DetailDto;
import com.summer.melisma.model.dto.PlaylistDetailDto;
import com.summer.melisma.model.entity.PlaylistEntity;
import com.summer.melisma.model.repository.PlaylistRepository;
import com.summer.melisma.model.vo.PlaylistVo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
@Transactional
public class PlaylistServiceTest {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistService playlistService;

    @InjectMocks
    private PlaylistService mockPlaylistService;

    @MockBean
    private PlaylistRepository mockPlaylistRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("플레이리스트 단일 생성")
    void 플레이리스트_단일생성() {
        // given
        List<DetailDto> details = new ArrayList<>();
        
        for(int i = 0; i < 3; i++) {
            UUID detailId = UUID.randomUUID();
            UUID musicId = UUID.randomUUID();

            DetailDto detail = DetailDto.builder()
                .id(detailId)
                .musicId(musicId)
                .build();

            details.add(detail);
        }

        PlaylistDetailDto playlistDetail = PlaylistDetailDto.builder().details(details).build();

        UUID playlistId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        PlaylistEntity playlistEntity = PlaylistEntity.builder()
            .id(playlistId)
            .playlistDetail(playlistDetail)
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .createdBy(userId)
            .build();

        // when
        playlistRepository.save(playlistEntity);
        Optional<PlaylistEntity> playlistOpt = playlistRepository.findById(playlistId);


        // then
        Assertions.assertThat(playlistOpt.get().getId()).isEqualTo(playlistId);
        Assertions.assertThat(playlistOpt.get().getCreatedBy()).isEqualTo(userId);
        for(int i = 0; i < 3; i++){
            Assertions.assertThat(playlistOpt.get().getPlaylistDetail().getDetails().get(i).getId()).isEqualTo(playlistEntity.getPlaylistDetail().getDetails().get(i).getId());
            Assertions.assertThat(playlistOpt.get().getPlaylistDetail().getDetails().get(i).getMusicId()).isEqualTo(playlistEntity.getPlaylistDetail().getDetails().get(i).getMusicId());
        }
    }

    @Test
    @DisplayName("플레이리스트 단일 조회")
    void 플레이리스트_단일조회() {
        // given
        List<DetailDto> details = new ArrayList<>();
        
        for(int i = 0; i < 3; i++) {
            UUID detailId = UUID.randomUUID();
            UUID musicId = UUID.randomUUID();

            DetailDto detail = DetailDto.builder()
                .id(detailId)
                .musicId(musicId)
                .build();

            details.add(detail);
        }

        PlaylistDetailDto playlistDetail = PlaylistDetailDto.builder().details(details).build();

        UUID playlistId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        PlaylistEntity playlistEntity = PlaylistEntity.builder()
            .id(playlistId)
            .playlistDetail(playlistDetail)
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .createdBy(userId)
            .build();

        // when
        playlistRepository.save(playlistEntity);
        PlaylistVo resultVo = playlistService.search(playlistId);
        
        // then
        Assertions.assertThat(playlistEntity.getId()).isEqualTo(resultVo.getId());
        for(int i = 0; i < 3; i++){
            Assertions.assertThat(playlistEntity.getPlaylistDetail().getDetails().get(i).getId()).isEqualTo(resultVo.getPlaylistDetail().getDetails().get(i).getId());
            Assertions.assertThat(playlistEntity.getPlaylistDetail().getDetails().get(i).getMusicId()).isEqualTo(resultVo.getPlaylistDetail().getDetails().get(i).getMusicId());
        }
    }

    @Test
    @DisplayName("플레이리스트 전체 조회")
    void 플레이리스트_전체조회() {
        // given
        List<PlaylistEntity> entities = new ArrayList<>();
        // Playlist1
        List<DetailDto> details = new ArrayList<>();
        
        for(int i = 0; i < 3; i++) {
            UUID detailId = UUID.randomUUID();
            UUID musicId = UUID.randomUUID();

            DetailDto detail = DetailDto.builder()
                .id(detailId)
                .musicId(musicId)
                .build();

            details.add(detail);
        }

        PlaylistDetailDto playlistDetail1 = PlaylistDetailDto.builder().details(details).build();

        UUID playlistId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        PlaylistEntity playlistEntity1 = PlaylistEntity.builder()
            .id(playlistId)
            .playlistDetail(playlistDetail1)
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .createdBy(userId)
            .build();
        
        entities.add(playlistEntity1);

        // Playlist2
        details = new ArrayList<>();
        
        for(int i = 0; i < 3; i++) {
            UUID detailId = UUID.randomUUID();
            UUID musicId = UUID.randomUUID();

            DetailDto detail = DetailDto.builder()
                .id(detailId)
                .musicId(musicId)
                .build();

            details.add(detail);
        }

        PlaylistDetailDto playlistDetail2 = PlaylistDetailDto.builder().details(details).build();

        UUID playlistId2 = UUID.randomUUID();

        PlaylistEntity playlistEntity2 = PlaylistEntity.builder()
            .id(playlistId2)
            .playlistDetail(playlistDetail2)
            .createdAt(LocalDateTime.now())
            .updatedAt(null)
            .createdBy(userId)
            .build();

        entities.add(playlistEntity2);

        // when
        Mockito.when(mockPlaylistRepository.findAll()).thenReturn(entities);
        List<PlaylistVo> results = playlistService.searchList();

        // then
        Assertions.assertThat(results.size()).isEqualTo(2);
    }
}
