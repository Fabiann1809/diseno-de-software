package com.quizai.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "resultados")
public class Resultado {

    @Id
    private String id;

    @Indexed(unique = true)
    private String quizId;

    @Indexed
    private String usuarioId;

    private List<Respuesta> respuestas = new ArrayList<>();
    private int totalCorrectas;
    private int totalIncorrectas;
    private double porcentajePuntaje;
    private LocalDateTime enviadoEn = LocalDateTime.now();

    public Resultado(String quizId, String usuarioId) {
        this.quizId = quizId;
        this.usuarioId = usuarioId;
    }

    public void agregarRespuesta(Respuesta respuesta) {
        this.respuestas.add(respuesta);
    }

    public void calcular() {
        this.totalCorrectas = (int) respuestas.stream().filter(Respuesta::isEsCorrecta).count();
        this.totalIncorrectas = respuestas.size() - this.totalCorrectas;
        this.porcentajePuntaje = respuestas.isEmpty() ? 0
                : (double) totalCorrectas / respuestas.size() * 100;
    }
}
