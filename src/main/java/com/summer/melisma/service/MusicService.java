package com.summer.melisma.service;

import com.summer.melisma.mapper.MusicMapper;
import com.summer.melisma.model.dto.MusicDto;
import com.summer.melisma.model.entity.MusicEntity;
import com.summer.melisma.model.vo.MusicVo;
import com.summer.melisma.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class MusicService {

    @Autowired
    MusicRepository musicRepository;

    @Autowired
    MusicMapper musicMapper;

    public MusicVo create(MusicDto dto){
        MusicEntity entity = MusicEntity.toEntity(dto);
        musicRepository.save(entity);

        MusicVo vo = MusicVo.toVo(MusicDto.toDto(entity));

        return vo;
    }

    public List<MusicVo> searchList(){
        List<MusicEntity> entities = musicRepository.findAll();
        List<MusicVo> vos = new ArrayList<>();
        for (MusicEntity entity: entities){
            vos.add(MusicVo.toVo(MusicDto.toDto(entity)));
        }

        return vos;
    }

    public MusicVo search(UUID id){
        MusicEntity entity = musicRepository.findById(id).get();
        return MusicVo.toVo(MusicDto.toDto(entity));
    }

    public void delete(UUID id){
        musicRepository.deleteById(id);
    }

    public void update(MusicDto dto){
        musicRepository.findById(dto.getId()).ifPresentOrElse(musicEntity -> {
            MusicEntity newEntity = MusicEntity.toEntity(dto);
            musicEntity.setMusicUrl(newEntity.getMusicUrl());
            musicEntity.setViews(newEntity.getViews());
            musicEntity.setCreatedBy(newEntity.getCreatedBy());
            musicRepository.save(musicEntity);
        }, null);;
    }

    public void change(UUID id, MusicDto dto){
        musicRepository.findById(id).ifPresentOrElse(musicEntity -> {
            MusicEntity newEntity = MusicEntity.toEntity(dto);
            if(newEntity.getMusicUrl() != null){
                musicEntity.setMusicUrl(newEntity.getMusicUrl());
            }
            if(newEntity.getViews() != null){
                musicEntity.setViews(newEntity.getViews());
            }
            if(newEntity.getCreatedBy() != null){
                musicEntity.setCreatedBy(newEntity.getCreatedBy());
            }

            musicRepository.save(musicEntity);

        }, null );
    }

    public boolean isEmpty(UUID id){
        return musicRepository.findById(id).isEmpty();
    }
}
