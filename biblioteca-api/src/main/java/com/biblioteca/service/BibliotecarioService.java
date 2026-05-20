package com.biblioteca.service;

import com.biblioteca.dto.PrestamoRequest;
import com.biblioteca.dto.PrestamoResponse;

public interface BibliotecarioService {

    PrestamoResponse registrarPrestamo(String bibliotecarioId, PrestamoRequest request);

    PrestamoResponse registrarDevolucion(String bibliotecarioId, String prestamoId);
}
