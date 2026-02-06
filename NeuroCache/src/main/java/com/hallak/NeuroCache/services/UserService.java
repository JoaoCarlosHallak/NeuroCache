package com.hallak.NeuroCache.services;

import com.hallak.NeuroCache.dtos.LoginRequestDTO;
import com.hallak.NeuroCache.dtos.SignupRequestDTO;
import com.hallak.NeuroCache.dtos.TokenResponseDTO;
import com.hallak.NeuroCache.dtos.UserResponseDTO;
import com.hallak.NeuroCache.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public interface UserService {
    UserResponseDTO signup(SignupRequestDTO signupRequestDTO);

    TokenResponseDTO login(LoginRequestDTO loginRequestDTO);


    User getAuthenticatedUser();
}
