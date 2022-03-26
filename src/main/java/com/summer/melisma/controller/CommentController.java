package com.summer.melisma.controller;

import com.summer.melisma.model.dto.CommentDto;
import com.summer.melisma.service.CommentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CommentDto dto) {
        commentService.create(dto);

        // TODO :: 리턴값 수정해야 함
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
