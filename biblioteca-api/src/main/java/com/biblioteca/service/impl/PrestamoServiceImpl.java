package com.biblioteca.service.impl;

import com.biblioteca.dto.PrestamoResponse;
import com.biblioteca.model.Ejemplar;
import com.biblioteca.model.Prestamo;
import com.biblioteca.repository.EjemplarRepository;
import com.biblioteca.repository.PrestamoRepository;
import com.biblioteca.service.PrestamoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final EjemplarRepository ejemplarRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository,
                               EjemplarRepository ejemplarRepository) {
        this.prestamoRepository = prestamoRepository;
        this.ejemplarRepository = ejemplarRepository;
    }

    @Override
    public PrestamoResponse consultarPrestamo(String id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prestamo no encontrado con id: " + id));
        return mapToResponse(prestamo);
    }

    @Override
    public List<PrestamoResponse> listarPrestamos() {
        return prestamoRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrestamoResponse> listarPrestamosPorUsuario(String usuarioId) {
        return prestamoRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public double calcularMora(String prestamoId) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new RuntimeException("Prestamo no encontrado con id: " + prestamoId));
        return prestamo.calcularMora();
    }

    @Override
    public PrestamoResponse cancelarPrestamo(String prestamoId) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new RuntimeException("Prestamo no encontrado con id: " + prestamoId));

        if (!"ACTIVO".equals(prestamo.getEstado())) {
            throw new RuntimeException("Solo se pueden cancelar prestamos activos. Estado actual: " + prestamo.getEstado());
        }

        Ejemplar ejemplar = ejemplarRepository.findById(prestamo.getEjemplarId())
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado con id: " + prestamo.getEjemplarId()));

        ejemplar.marcarDisponible();
        ejemplarRepository.save(ejemplar);

        prestamo.cancelarPrestamo();
        return mapToResponse(prestamoRepository.save(prestamo));
    }

    private PrestamoResponse mapToResponse(Prestamo p) {
        return new PrestamoResponse(p.getId(), p.getUsuarioId(), p.getEjemplarId(),
                p.getFechaPrestamo(), p.getFechaDevolucionEsperada(),
                p.getFechaDevolucionReal(), p.getEstado(), p.calcularMora());
    }
}
