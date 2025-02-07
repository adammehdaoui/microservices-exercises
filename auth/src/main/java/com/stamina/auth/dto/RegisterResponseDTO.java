package com.stamina.auth.dto;

import org.springframework.http.HttpStatus;

public record RegisterResponseDTO(HttpStatus code, String message) {
}
