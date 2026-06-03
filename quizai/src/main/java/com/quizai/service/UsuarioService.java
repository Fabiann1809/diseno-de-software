package com.quizai.service;

import com.quizai.dto.EstadisticasDTO;
import com.quizai.model.Usuario;

public interface UsuarioService {
    EstadisticasDTO obtenerEstadisticas(String usuarioId);
    void actualizarEstadisticas(String usuarioId, double puntaje, String tema);
    void incrementarTotalQuizzes(String usuarioId);
    Usuario buscarPorId(String usuarioId);
}
