package com.quizai.dto;

import com.quizai.model.Dificultad;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GenerarDesdeTextoRequest(
        @NotBlank(message = "El texto es obligatorio")
        @Size(min = 50, message = "El texto debe tener al menos 50 caracteres")
        String texto,

        @NotNull(message = "La dificultad es obligatoria")
        Dificultad dificultad,

        @Min(value = 3, message = "Mínimo 3 preguntas")
        @Max(value = 20, message = "Máximo 20 preguntas")
        int cantidad
) {}
