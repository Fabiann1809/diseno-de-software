package com.quizai.service.impl;

import com.quizai.dto.EstadisticasDTO;
import com.quizai.exception.RecursoNoEncontradoException;
import com.quizai.model.Usuario;
import com.quizai.repository.UsuarioRepository;
import com.quizai.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public EstadisticasDTO obtenerEstadisticas(String usuarioId) {
        Usuario usuario = buscarPorId(usuarioId);
        return new EstadisticasDTO(
                usuario.getTotalQuizzes(),
                usuario.getTotalRespondidos(),
                usuario.getPromedioPuntaje(),
                usuario.getTemasFrecuentes()
        );
    }

    @Override
    public void actualizarEstadisticas(String usuarioId, double puntaje, String tema) {
        Usuario usuario = buscarPorId(usuarioId);
        usuario.actualizarEstadisticas(puntaje, tema);
        usuarioRepository.save(usuario);
        log.info("Estadísticas actualizadas para usuario: {}", usuarioId);
    }

    @Override
    public void incrementarTotalQuizzes(String usuarioId) {
        Usuario usuario = buscarPorId(usuarioId);
        usuario.setTotalQuizzes(usuario.getTotalQuizzes() + 1);
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario buscarPorId(String usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado: " + usuarioId));
    }
}
