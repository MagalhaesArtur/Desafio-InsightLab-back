package com.desafioInsightLab.dtos;

import com.desafioInsightLab.domain.user.UserRole;

public record RegisterDTO(String username, String password, UserRole role) {
}
