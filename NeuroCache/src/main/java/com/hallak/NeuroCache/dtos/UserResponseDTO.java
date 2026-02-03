package com.hallak.NeuroCache.dtos;

import com.hallak.NeuroCache.entities.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private String email;
    private Set<RoleName> roles;

}
