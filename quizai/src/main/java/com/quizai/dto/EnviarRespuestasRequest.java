package com.quizai.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record EnviarRespuestasRequest(
        @NotEmpty(message = "Debes enviar al menos una respuesta")
        @Valid
        List<RespuestaRequest> respuestas
) {}
