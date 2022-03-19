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

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
@Transactional
public class PlaylistServiceTest {
    @Autowired
    private PlaylistRepository playlistRepository;

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
}
