package com.desafioInsightLab.dtos;

import java.util.UUID;

public record UserDTO(String username, String email, UUID id) {
}
