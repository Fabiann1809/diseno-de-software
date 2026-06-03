package com.quizai.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class FiltroJwt extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(FiltroJwt.class);

    private final JwtUtil jwtUtil;

    public FiltroJwt(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String encabezado = request.getHeader("Authorization");

        if (encabezado != null && encabezado.startsWith("Bearer ")) {
            String token = encabezado.substring(7);
            if (jwtUtil.validarToken(token)) {
                String usuarioId = jwtUtil.extraerUsuarioId(token);
                UsernamePasswordAuthenticationToken autenticacion =
                        new UsernamePasswordAuthenticationToken(usuarioId, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(autenticacion);
                log.debug("Usuario autenticado via JWT: {}", usuarioId);
            }
        }

        filterChain.doFilter(request, response);
    }
}
