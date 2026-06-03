package com.quizai.controller;

import com.quizai.dto.EstadisticasDTO;
import com.quizai.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Estadísticas del usuario autenticado")
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/me/estadisticas")
    @Operation(summary = "Obtener estadísticas del usuario autenticado")
    public ResponseEntity<EstadisticasDTO> obtenerEstadisticas(Authentication auth) {
        return ResponseEntity.ok(usuarioService.obtenerEstadisticas(auth.getName()));
    }
}
