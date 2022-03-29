package com.summer.melisma.controller;

import com.summer.melisma.model.users.dto.LoginReqUserDto;
import com.summer.melisma.model.users.dto.UserDto;
import com.summer.melisma.service.UserService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody LoginReqUserDto reqDto) {
        String message = "success";
        
        try{
            userService.create(reqDto);
        } catch (DataIntegrityViolationException e) {
            message = "duplicate username";
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqUserDto reqDto) {
        String message = "";

        try{
            message = userService.login(reqDto).toString();
        } catch (Exception e) {
            message = "fail";
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
