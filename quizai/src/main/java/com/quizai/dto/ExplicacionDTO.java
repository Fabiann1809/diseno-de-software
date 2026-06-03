package com.quizai.dto;

public record ExplicacionDTO(
        String preguntaId,
        String textoPregunta,
        String textoOpcionCorrecta,
        String explicacion
) {}
