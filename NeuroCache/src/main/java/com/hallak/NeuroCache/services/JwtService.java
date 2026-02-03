package com.hallak.NeuroCache.services;

import com.hallak.NeuroCache.entities.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


public interface JwtService {
    String generate(User user);

    String extractEmail(String token);

    String extractUserId(String token);

    boolean isValid(String token, UserDetails user);


}
