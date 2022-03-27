package com.summer.melisma.controller;

import com.summer.melisma.model.MusicDto;
import com.summer.melisma.model.MusicVo;
import com.summer.melisma.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/musics")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @PostMapping
    public ResponseEntity create(@RequestBody MusicDto dto){
        MusicVo vo = musicService.create(dto);
        return ResponseEntity.ok(vo);
    }

    @GetMapping
    public ResponseEntity readAll(){
        List<MusicVo> vos = musicService.readAll();
        return ResponseEntity.ok(vos);
    }

    @GetMapping("/readOne/{id}")
    public ResponseEntity readOne(@PathVariable UUID id){
        MusicVo vo = musicService.readOne(id);
        return ResponseEntity.ok(vo);

    }
}
