package com.hallak.NeuroCache.controllers;

import com.hallak.NeuroCache.dtos.LoginRequestDTO;
import com.hallak.NeuroCache.dtos.SignupRequestDTO;

import com.hallak.NeuroCache.dtos.TokenResponseDTO;
import com.hallak.NeuroCache.dtos.UserResponseDTO;
import com.hallak.NeuroCache.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/auth")
@RestController
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signup(@RequestBody SignupRequestDTO signupRequestDTO) {
        return new ResponseEntity<>(userService.signup(signupRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return new ResponseEntity<>(userService.login(loginRequestDTO), HttpStatus.OK);
    }

}
