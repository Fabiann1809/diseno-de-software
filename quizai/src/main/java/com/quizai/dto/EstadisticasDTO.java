package com.quizai.dto;

import java.util.List;

public record EstadisticasDTO(
        int totalQuizzes,
        int totalRespondidos,
        double promedioPuntaje,
        List<String> temasFrecuentes
) {}
