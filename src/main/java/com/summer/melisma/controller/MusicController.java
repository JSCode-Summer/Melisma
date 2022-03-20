package com.summer.melisma.controller;

import com.summer.melisma.model.MusicDto;
import com.summer.melisma.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/musics")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @PostMapping
    public ResponseEntity create(@RequestBody MusicDto dto){
        return ResponseEntity.ok(musicService.create(dto));
    }
}
