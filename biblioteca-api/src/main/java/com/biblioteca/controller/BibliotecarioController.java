package com.biblioteca.controller;

import com.biblioteca.dto.PrestamoRequest;
import com.biblioteca.dto.PrestamoResponse;
import com.biblioteca.service.BibliotecarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bibliotecarios")
public class BibliotecarioController {

    private final BibliotecarioService bibliotecarioService;

    public BibliotecarioController(BibliotecarioService bibliotecarioService) {
        this.bibliotecarioService = bibliotecarioService;
    }

    // registrarPrestamo() del diagrama
    @PostMapping("/{id}/prestamos")
    public ResponseEntity<PrestamoResponse> registrarPrestamo(
            @PathVariable String id, @RequestBody PrestamoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bibliotecarioService.registrarPrestamo(id, request));
    }

    @PostMapping("/{id}/prestamos/{prestamoId}/devolucion")
    public ResponseEntity<PrestamoResponse> registrarDevolucion(
            @PathVariable String id, @PathVariable String prestamoId) {
        return ResponseEntity.ok(bibliotecarioService.registrarDevolucion(id, prestamoId));
    }
}
