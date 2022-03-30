package com.summer.melisma.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.summer.melisma.model.playlists.dto.PlaylistDto;
import com.summer.melisma.model.playlists.entity.PlaylistEntity;
import com.summer.melisma.model.playlists.repository.PlaylistRepository;
import com.summer.melisma.model.playlists.vo.PlaylistVo;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    /**
     * <b>단일 playlist 생성</b>
     * <p>
     * 
     * @param dto
     */
    public void create(PlaylistDto dto) {
        PlaylistEntity entity = PlaylistEntity.toEntity(dto);
        
        UUID userId = UUID.randomUUID();    // TODO:: 실제 userId로 변경
        entity.setCreatedAt(LocalDateTime.now()).setCreatedBy(userId);

        playlistRepository.save(entity);
    }

    /**
     * <b>playlis 전체 조회</b>
     * <p>
     *
     * @return List[PlaylistEntity]
     * @see PlaylistRepository#findAll
     * @see PlaylistVo#toVo
     */
    public List<PlaylistVo> searchList() {
        // TODO::userId에 대응되는 데이터만 조회하도록 변경
        List<PlaylistEntity> entities = playlistRepository.findAll();
        return entities.stream().map(entity -> PlaylistVo.toVo(entity)).collect(Collectors.toList());
    }

    /**
     * <b>id값에 대응하는 playlis 단일 조회</b>
     * <p>
     * 
     * @param id : UUID
     * @return PlaylistVo
     * @see PlaylistRepository#findById
     * @see PlaylistVo#toVo
     * @throws NullPointerException
     */
    public PlaylistVo search(UUID id) {
        Optional<PlaylistEntity> entityOpt = playlistRepository.findById(id);

        if(entityOpt.isPresent()) {
            return PlaylistVo.toVo(entityOpt.get());
        }else {
            throw new NullPointerException();
        }
    }

    /**
     * <b></b>
     * <p>
     * 
     * @param id : UUID
     * @see PlaylistRepository#findById
     * @see PlatlistRepository#delete
     * @throws NullPointerException
     */
    public void delete(UUID id) {
        Optional<PlaylistEntity> entityOpt = playlistRepository.findById(id);

        if(entityOpt.isPresent()) {
            playlistRepository.delete(entityOpt.get());
        }else {
            throw new NullPointerException();
        }
    }

    public void update(PlaylistDto dto) {
        playlistRepository.findById(dto.getId()).ifPresentOrElse(entity -> {
            entity.setPlaylistDetail(dto.getPlaylistDetail()).setUpdatedAt(LocalDateTime.now());
            playlistRepository.save(entity);
        }, null);;
    }

    public void change(PlaylistDto dto) {
        playlistRepository.findById(dto.getId()).ifPresentOrElse(entity -> {
            if(dto.getPlaylistDetail() != null) {
                entity.setPlaylistDetail(dto.getPlaylistDetail());
            }

            entity.setUpdatedAt(LocalDateTime.now());
            playlistRepository.save(entity);
        }, null);
    }
}
