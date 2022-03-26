package com.summer.melisma.controller;

import com.summer.melisma.model.MusicDto;
import com.summer.melisma.model.MusicVo;
import com.summer.melisma.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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


}
