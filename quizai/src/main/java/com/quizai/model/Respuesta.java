package com.quizai.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Respuesta {

    private String id;
    private String resultadoId;
    private String preguntaId;
    private String opcionSeleccionadaId;
    private boolean esCorrecta;

    public Respuesta(String resultadoId, String preguntaId, String opcionSeleccionadaId) {
        this.id = UUID.randomUUID().toString();
        this.resultadoId = resultadoId;
        this.preguntaId = preguntaId;
        this.opcionSeleccionadaId = opcionSeleccionadaId;
    }

    public void evaluar(String opcionCorrectaId) {
        this.esCorrecta = opcionCorrectaId != null && opcionCorrectaId.equals(this.opcionSeleccionadaId);
    }
}
