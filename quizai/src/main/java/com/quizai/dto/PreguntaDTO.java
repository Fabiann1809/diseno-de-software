package com.quizai.dto;

import java.util.List;

public record PreguntaDTO(
        String id,
        String textoPregunta,
        List<OpcionDTO> opciones,
        int orden
) {}
