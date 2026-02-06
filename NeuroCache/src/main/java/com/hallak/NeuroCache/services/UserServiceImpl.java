package com.hallak.NeuroCache.services;


import com.hallak.NeuroCache.dtos.LoginRequestDTO;
import com.hallak.NeuroCache.dtos.SignupRequestDTO;
import com.hallak.NeuroCache.dtos.TokenResponseDTO;
import com.hallak.NeuroCache.dtos.UserResponseDTO;
import com.hallak.NeuroCache.entities.RoleName;
import com.hallak.NeuroCache.entities.User;
import com.hallak.NeuroCache.entities.UserMain;
import com.hallak.NeuroCache.exceptions.UserAlreadyExistsException;
import com.hallak.NeuroCache.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public UserResponseDTO signup(SignupRequestDTO signupRequestDTO) {
        if (userRepository.findByEmail(signupRequestDTO.email()).isPresent()) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = new User();
        user.setEmail(signupRequestDTO.email());
        user.setPasswordHash(bCryptPasswordEncoder.encode(signupRequestDTO.password()));
        user.setRoles(Set.of(RoleName.USER));
        return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
    }

    @Override
    public TokenResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password())
        );

        UserMain user = (UserMain) auth.getPrincipal();
        String token = jwtService.generate(user.getUser());

        return new TokenResponseDTO(token);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return ((UserMain) authentication.getPrincipal()).getUser();
    }






}
