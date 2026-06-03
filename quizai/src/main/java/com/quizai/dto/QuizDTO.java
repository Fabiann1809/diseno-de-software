package com.quizai.dto;

import com.quizai.model.Dificultad;
import com.quizai.model.EstadoQuiz;

import java.time.LocalDateTime;
import java.util.List;

public record QuizDTO(
        String id,
        String tema,
        Dificultad dificultad,
        int totalPreguntas,
        EstadoQuiz estado,
        Double puntaje,
        LocalDateTime creadoEn,
        List<PreguntaDTO> preguntas
) {}
