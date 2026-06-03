package com.quizai.dto;

import jakarta.validation.constraints.NotBlank;

public record RespuestaRequest(
        @NotBlank(message = "El ID de la pregunta es obligatorio")
        String preguntaId,

        @NotBlank(message = "El ID de la opción seleccionada es obligatorio")
        String opcionSeleccionadaId
) {}
