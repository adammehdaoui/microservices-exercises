package com.stamina.auth.controller;

import com.stamina.auth.dto.LoginQueryDTO;
import com.stamina.auth.dto.LoginResponseDTO;
import com.stamina.auth.dto.RegisterResponseDTO;
import com.stamina.auth.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginQueryDTO loginQueryDTO) {
        LoginResponseDTO loginResponse = memberService.login(loginQueryDTO.username(), loginQueryDTO.password());

        return ResponseEntity.status(loginResponse.code()).body(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody LoginQueryDTO loginQueryDTO) {
        RegisterResponseDTO loginResponse = memberService.register(loginQueryDTO.username(), loginQueryDTO.password());

        return ResponseEntity.status(loginResponse.code()).body(loginResponse);
    }

}
