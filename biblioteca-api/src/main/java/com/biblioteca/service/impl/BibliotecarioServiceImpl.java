package com.biblioteca.service.impl;

import com.biblioteca.dto.PrestamoRequest;
import com.biblioteca.dto.PrestamoResponse;
import com.biblioteca.model.Bibliotecario;
import com.biblioteca.model.Ejemplar;
import com.biblioteca.model.Prestamo;
import com.biblioteca.model.Usuario;
import com.biblioteca.repository.EjemplarRepository;
import com.biblioteca.repository.PrestamoRepository;
import com.biblioteca.repository.UsuarioRepository;
import com.biblioteca.service.BibliotecarioService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BibliotecarioServiceImpl implements BibliotecarioService {

    private final UsuarioRepository usuarioRepository;
    private final PrestamoRepository prestamoRepository;
    private final EjemplarRepository ejemplarRepository;

    public BibliotecarioServiceImpl(UsuarioRepository usuarioRepository,
                                    PrestamoRepository prestamoRepository,
                                    EjemplarRepository ejemplarRepository) {
        this.usuarioRepository = usuarioRepository;
        this.prestamoRepository = prestamoRepository;
        this.ejemplarRepository = ejemplarRepository;
    }

    @Override
    public PrestamoResponse registrarPrestamo(String bibliotecarioId, PrestamoRequest request) {
        Usuario solicitante = usuarioRepository.findById(bibliotecarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + bibliotecarioId));

        if (!(solicitante instanceof Bibliotecario)) {
            throw new RuntimeException("Solo un bibliotecario puede registrar prestamos");
        }

        Usuario beneficiario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario beneficiario no encontrado con id: " + request.getUsuarioId()));

        if (beneficiario instanceof Bibliotecario) {
            throw new RuntimeException("Un bibliotecario no puede ser beneficiario de un prestamo");
        }

        Ejemplar ejemplar = ejemplarRepository.findById(request.getEjemplarId())
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado con id: " + request.getEjemplarId()));

        if (!"DISPONIBLE".equals(ejemplar.getEstado())) {
            throw new RuntimeException("El ejemplar no esta disponible. Estado actual: " + ejemplar.getEstado());
        }

        ejemplar.marcarPrestado();
        ejemplarRepository.save(ejemplar);

        Prestamo prestamo = new Prestamo();
        prestamo.setUsuarioId(request.getUsuarioId());
        prestamo.setEjemplarId(request.getEjemplarId());
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucionEsperada(request.getFechaDevolucionEsperada());
        prestamo.setEstado("ACTIVO");

        return mapToResponse(prestamoRepository.save(prestamo));
    }

    @Override
    public PrestamoResponse registrarDevolucion(String bibliotecarioId, String prestamoId) {
        Usuario solicitante = usuarioRepository.findById(bibliotecarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + bibliotecarioId));

        if (!(solicitante instanceof Bibliotecario)) {
            throw new RuntimeException("Solo un bibliotecario puede registrar devoluciones");
        }

        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new RuntimeException("Prestamo no encontrado con id: " + prestamoId));

        if (!"ACTIVO".equals(prestamo.getEstado())) {
            throw new RuntimeException("El prestamo no esta activo. Estado actual: " + prestamo.getEstado());
        }

        Ejemplar ejemplar = ejemplarRepository.findById(prestamo.getEjemplarId())
                .orElseThrow(() -> new RuntimeException("Ejemplar no encontrado con id: " + prestamo.getEjemplarId()));

        ejemplar.marcarDisponible();
        ejemplarRepository.save(ejemplar);

        prestamo.setFechaDevolucionReal(LocalDate.now());
        prestamo.setEstado("DEVUELTO");

        return mapToResponse(prestamoRepository.save(prestamo));
    }

    private PrestamoResponse mapToResponse(Prestamo p) {
        return new PrestamoResponse(p.getId(), p.getUsuarioId(), p.getEjemplarId(),
                p.getFechaPrestamo(), p.getFechaDevolucionEsperada(),
                p.getFechaDevolucionReal(), p.getEstado(), p.calcularMora());
    }
}
