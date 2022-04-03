package com.summer.melisma.model.dto;

import java.util.List;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class PlaylistDetailDto {
    // TODO:: 여기서 바로 MusicDto 리스트를 가져올지 생각해보기
    @Type(type = "jsonb")
    private List<DetailDto> details;
}
