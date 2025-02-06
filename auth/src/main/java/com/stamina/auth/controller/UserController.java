package com.stamina.auth.controller;

import com.stamina.auth.dto.LoginQueryDTO;
import com.stamina.auth.dto.LoginResponseDTO;
import com.stamina.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginQueryDTO loginQueryDTO) {
        LoginResponseDTO loginResponse = userService.login(loginQueryDTO.username(), loginQueryDTO.password());

        return ResponseEntity.status(loginResponse.code()).body(loginResponse);
    }

}
