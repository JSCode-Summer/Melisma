package com.summer.melisma.controller;

import com.summer.melisma.model.dto.PlaylistDto;
import com.summer.melisma.service.PlaylistService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/playlists")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PlaylistDto dto) {
        playlistService.create(dto);

        // TODO :: 리턴값 수정해야 함
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
