package com.stamina.auth.dto;

import org.springframework.http.HttpStatus;

public record LoginResponseDTO (HttpStatus code, String message, String token ) { }
