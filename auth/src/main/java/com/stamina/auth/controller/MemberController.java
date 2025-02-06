package com.stamina.auth.controller;

import com.stamina.auth.dto.LoginQueryDTO;
import com.stamina.auth.dto.LoginResponseDTO;
import com.stamina.auth.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
