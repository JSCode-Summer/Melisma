package com.summer.melisma.controller;

import com.summer.melisma.model.dto.MusicDto;
import com.summer.melisma.model.vo.MusicVo;
import com.summer.melisma.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody MusicDto dto){
        MusicVo vo = musicService.create(dto);
        return ResponseEntity.ok(vo);
    }

    @GetMapping("/searchList")
    public ResponseEntity searchList(){
        List<MusicVo> vos = musicService.readAll();
        return ResponseEntity.ok(vos);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity search(@PathVariable UUID id){
        MusicVo vo = musicService.readOne(id);
        return ResponseEntity.ok(vo);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        musicService.delete(id);

        return ResponseEntity.ok("Music is deleted");
    }



}
