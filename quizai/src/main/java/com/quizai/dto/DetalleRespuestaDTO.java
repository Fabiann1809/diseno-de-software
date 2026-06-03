package com.quizai.dto;

public record DetalleRespuestaDTO(
        String preguntaId,
        String opcionSeleccionadaId,
        String opcionCorrectaId,
        boolean esCorrecta
) {}
