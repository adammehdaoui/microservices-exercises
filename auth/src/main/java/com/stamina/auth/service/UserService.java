package com.stamina.auth.service;

import com.stamina.auth.dto.LoginResponseDTO;
import com.stamina.auth.entity.User;
import com.stamina.auth.repository.UserRepository;
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
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponseDTO login(String username, String password) {
        User u = userRepository.findByUsername(username);

        if (u == null) {
            return new LoginResponseDTO(HttpStatus.NOT_FOUND, "User not found", null);
        }

        if (!u.getPassword().equals(password)) {
            return new LoginResponseDTO(HttpStatus.UNAUTHORIZED, "Invalid password", null);
        }

        String token = generateToken(username);

        return new LoginResponseDTO(HttpStatus.OK, "Login successful", token);
    }

    public String generateToken(String username) {
        byte[] keyBytes = Decoders.BASE64.decode("4261656C64756E67");
        Key k = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
                .signWith(k)
                .compact();
    }

}
