package com.hallak.NeuroCache.dtos;

import lombok.Data;
import lombok.Getter;

public record SignupRequestDTO(String email, String password) {
}
