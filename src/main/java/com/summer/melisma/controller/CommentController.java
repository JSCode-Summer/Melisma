package com.summer.melisma.controller;

import java.util.List;
import java.util.UUID;

import com.summer.melisma.model.dto.CommentDto;
import com.summer.melisma.model.vo.CommentVo;
import com.summer.melisma.service.CommentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @GetMapping("/searchList")
    public ResponseEntity<?> searchList() {
        List<CommentVo> vos = commentService.searchList();

        return new ResponseEntity<>(vos, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> search(@PathVariable UUID id) {
        CommentVo vo = new CommentVo();
        
        try{
            vo = commentService.search(id);
        } catch (NullPointerException e) {
            vo = null;
        }

        return new ResponseEntity<>(vo, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        String message = "success";

        try {
            commentService.delete(id);
        } catch (NullPointerException e) {
            message = "not found";
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update( @RequestBody CommentDto dto) {
        return new ResponseEntity<>(commentService.update(dto), HttpStatus.OK);
    }
    @PatchMapping("/change")
    public ResponseEntity<?> change( @RequestBody CommentDto dto) {
        return new ResponseEntity<>(commentService.change(dto), HttpStatus.OK);
    }
}
