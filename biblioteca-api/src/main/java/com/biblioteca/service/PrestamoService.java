package com.biblioteca.service;

import com.biblioteca.dto.PrestamoResponse;

import java.util.List;

public interface PrestamoService {

    PrestamoResponse consultarPrestamo(String id);

    List<PrestamoResponse> listarPrestamos();

    List<PrestamoResponse> listarPrestamosPorUsuario(String usuarioId);

    double calcularMora(String prestamoId);

    PrestamoResponse cancelarPrestamo(String prestamoId);
}
