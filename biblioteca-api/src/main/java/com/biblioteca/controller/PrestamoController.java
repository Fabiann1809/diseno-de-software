package com.biblioteca.controller;

import com.biblioteca.dto.PrestamoResponse;
import com.biblioteca.service.PrestamoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public ResponseEntity<List<PrestamoResponse>> listarPrestamos() {
        return ResponseEntity.ok(prestamoService.listarPrestamos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoResponse> consultarPrestamo(@PathVariable String id) {
        return ResponseEntity.ok(prestamoService.consultarPrestamo(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PrestamoResponse>> listarPorUsuario(@PathVariable String usuarioId) {
        return ResponseEntity.ok(prestamoService.listarPrestamosPorUsuario(usuarioId));
    }

    // calcularMora() del diagrama
    @GetMapping("/{id}/mora")
    public ResponseEntity<Double> calcularMora(@PathVariable String id) {
        return ResponseEntity.ok(prestamoService.calcularMora(id));
    }

    // cancelarPrestamo() del diagrama
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<PrestamoResponse> cancelarPrestamo(@PathVariable String id) {
        return ResponseEntity.ok(prestamoService.cancelarPrestamo(id));
    }
}
