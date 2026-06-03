package com.quizai.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ResultadoDTO(
        String id,
        String quizId,
        int totalCorrectas,
        int totalIncorrectas,
        double porcentajePuntaje,
        LocalDateTime enviadoEn,
        List<DetalleRespuestaDTO> respuestas
) {}
