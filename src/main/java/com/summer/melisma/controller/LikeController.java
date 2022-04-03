package com.summer.melisma.controller;

import java.util.List;
import java.util.UUID;

import com.summer.melisma.model.dto.LikeDto;
import com.summer.melisma.service.LikeService;
import com.summer.melisma.model.vo.LikeVo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody LikeDto dto) {
        likeService.create(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> search(@PathVariable UUID id){
        LikeVo vo = new LikeVo();

        try{
            vo = likeService.search(id);
        } catch (NullPointerException e) {
            vo = null;
        }
        return new ResponseEntity<>(vo, HttpStatus.OK);

    }

    @GetMapping("/searchList")
    public ResponseEntity<?> searchList() {
        List<LikeVo> vos = likeService.searchList();

        return new ResponseEntity<>(vos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        String message = "success";

        try {
            likeService.delete(id);
        } catch (NullPointerException e) {
            message = "not found";
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
