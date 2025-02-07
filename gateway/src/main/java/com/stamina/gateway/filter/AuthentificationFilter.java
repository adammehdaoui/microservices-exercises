package com.stamina.gateway.filter;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class AuthentificationFilter extends AbstractGatewayFilterFactory<AuthentificationFilter.Config> {

    private final String secretKey = "4enGpPAg6GSKkQn1dnEBmPzrNWNhbIVkF2gMpzWqVDvHFAJZiQtnS46DkmPr3vMyFkBEtYshlKtmghF16MYEBPHlLSPUc1sXANgSHvSYktU=";

    public AuthentificationFilter() {
        super(Config.class);
    }

    public static class Config {
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            String token = authHeader.substring(7);
            try {
                validateToken(token);
            } catch (Exception e) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            return chain.filter(exchange);
        };
    }

    private void validateToken(String token) {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        Jwts.parser().verifyWith(key).build().parse(token);
    }
}
