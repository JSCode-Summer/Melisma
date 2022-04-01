package com.summer.melisma.mapper;

import com.summer.melisma.model.dto.MusicDto;
import com.summer.melisma.model.entity.MusicEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MusicMapper {

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void changeMusicEntityFromDto(MusicDto musicDto, @MappingTarget MusicEntity musicEntity);
//
//    void updateMusicEntityFromDto(MusicDto musicDto, @MappingTarget MusicEntity musicEntity);
}
