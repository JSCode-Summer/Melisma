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

    public MusicVo update(MusicDto dto){
        MusicEntity entity = musicRepository.findById(dto.getId()).get();
        musicMapper.updateMusicEntityFromDto(dto, entity);
        musicRepository.save(entity);

        return MusicVo.toVo(MusicDto.toDto(entity));
    }

    public MusicVo change(UUID id, MusicDto dto){
        MusicEntity entity = musicRepository.findById(id).get();
//        entity = musicMapper.musicDtoToMusicEntity(dto);
        musicMapper.changeMusicEntityFromDto(dto, entity);
        musicRepository.save(entity);
        return MusicVo.toVo(MusicDto.toDto(entity));
    }

    public boolean isEmpty(UUID id){
        return musicRepository.findById(id).isEmpty();
    }
}
