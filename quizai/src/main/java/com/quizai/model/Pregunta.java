package com.quizai.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "preguntas")
public class Pregunta {

    @Id
    private String id;

    @Indexed
    private String quizId;

    private String textoPregunta;
    private List<Opcion> opciones = new ArrayList<>();
    private String opcionCorrectaId;
    private String explicacion;
    private int orden;

    public Pregunta(String quizId, String textoPregunta, String opcionCorrectaId, int orden) {
        this.quizId = quizId;
        this.textoPregunta = textoPregunta;
        this.opcionCorrectaId = opcionCorrectaId;
        this.orden = orden;
    }

    public void agregarOpcion(Opcion opcion) {
        this.opciones.add(opcion);
    }

    public Opcion obtenerOpcionCorrecta() {
        return opciones.stream()
                .filter(o -> o.getId().equals(opcionCorrectaId))
                .findFirst()
                .orElse(null);
    }

    public boolean tieneExplicacion() {
        return explicacion != null && !explicacion.isBlank();
    }
}
