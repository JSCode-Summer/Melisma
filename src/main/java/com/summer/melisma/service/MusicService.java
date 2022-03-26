package com.summer.melisma.service;

import com.summer.melisma.model.MusicDto;
import com.summer.melisma.model.MusicEntity;
import com.summer.melisma.model.MusicVo;
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

    public MusicVo create(MusicDto dto){
        MusicEntity entity = MusicEntity.toEntity(dto);
        musicRepository.save(entity);

        MusicVo vo = MusicVo.toVo(dto);

        return vo;
    }

    public List<MusicVo> readAll(){
        List<MusicEntity> entities = musicRepository.findAll();
        List<MusicVo> vos = new ArrayList<>();
        for (MusicEntity entity: entities){
            vos.add(MusicVo.toVo(MusicDto.toDto(entity)));
        }

        return vos;
    }
}
