package com.quizai.service;

import com.quizai.dto.GenerarDesdeTextoRequest;
import com.quizai.dto.GenerarQuizRequest;
import com.quizai.dto.QuizDTO;
import com.quizai.model.Quiz;

import java.util.List;

public interface QuizService {
    QuizDTO generar(String usuarioId, GenerarQuizRequest request);
    QuizDTO generarDesdeTexto(String usuarioId, GenerarDesdeTextoRequest request);
    List<QuizDTO> buscarPorUsuario(String usuarioId);
    QuizDTO buscarPorId(String quizId, String usuarioId);
    void eliminar(String quizId, String usuarioId);
    Quiz obtenerQuiz(String quizId);
    void validarPropiedad(Quiz quiz, String usuarioId);
}
