package com.quizai.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    private final SecretKey claveSecreta;
    private final long expiracionMs;

    public JwtUtil(
            @Value("${app.jwt.secret}") String secreto,
            @Value("${app.jwt.expiration-ms}") long expiracionMs) {
        this.claveSecreta = Keys.hmacShaKeyFor(secreto.getBytes(StandardCharsets.UTF_8));
        this.expiracionMs = expiracionMs;
    }

    public String generarToken(String usuarioId) {
        return Jwts.builder()
                .subject(usuarioId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiracionMs))
                .signWith(claveSecreta)
                .compact();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parser().verifyWith(claveSecreta).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.warn("Token JWT inválido: {}", e.getMessage());
            return false;
        }
    }

    public String extraerUsuarioId(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(claveSecreta)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    public long getExpiracionMs() {
        return expiracionMs;
    }
}
