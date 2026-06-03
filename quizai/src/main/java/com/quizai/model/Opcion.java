package com.quizai.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class Opcion {

    private String id;
    private String preguntaId;
    private String etiqueta;
    private String texto;

    public Opcion(String preguntaId, String etiqueta, String texto) {
        this.id = UUID.randomUUID().toString();
        this.preguntaId = preguntaId;
        this.etiqueta = etiqueta;
        this.texto = texto;
    }
}
