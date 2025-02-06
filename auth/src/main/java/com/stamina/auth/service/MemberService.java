package com.stamina.auth.service;

import com.stamina.auth.dto.LoginResponseDTO;
import com.stamina.auth.entity.Member;
import com.stamina.auth.repository.MemberRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public LoginResponseDTO login(String username, String password) {
        Member member = memberRepository.findByUsername(username);

        if (member == null) {
            return new LoginResponseDTO(HttpStatus.NOT_FOUND, "User not found", null);
        }

        if (!member.getPassword().equals(password)) {
            return new LoginResponseDTO(HttpStatus.UNAUTHORIZED, "Invalid password", null);
        }

        String token = generateToken(username);

        return new LoginResponseDTO(HttpStatus.OK, "Login successful", token);
    }

    public String generateToken(String username) {
        String secretKey = "riUgWOCxqKwCHdEHnm8LI/9898HQ0fqLWNoDVKeYnK8=";
        byte[] keyBytes = Base64.getEncoder().encode(secretKey.getBytes());
        Key k = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
                .signWith(k)
                .compact();
    }

}
