package com.quizai.controller;

import com.quizai.dto.LoginRequest;
import com.quizai.dto.RegistroRequest;
import com.quizai.dto.TokenDTO;
import com.quizai.dto.UsuarioDTO;
import com.quizai.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticación", description = "Registro e inicio de sesión")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registro")
    @Operation(summary = "Registrar nuevo usuario")
    public ResponseEntity<UsuarioDTO> registrar(@Valid @RequestBody RegistroRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registrar(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión y obtener token JWT")
    public ResponseEntity<TokenDTO> iniciarSesion(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.iniciarSesion(request));
    }
}
