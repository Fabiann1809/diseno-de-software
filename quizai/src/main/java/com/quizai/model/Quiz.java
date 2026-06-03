package com.quizai.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "quizzes")
public class Quiz {

    @Id
    private String id;

    @Indexed
    private String usuarioId;

    private String tema;
    private String textoFuente;
    private Dificultad dificultad;
    private int totalPreguntas;
    private EstadoQuiz estado = EstadoQuiz.PENDIENTE;
    private Double puntaje;
    private LocalDateTime creadoEn = LocalDateTime.now();
    private LocalDateTime respondidoEn;

    public Quiz(String usuarioId, String tema, Dificultad dificultad, int totalPreguntas) {
        this.usuarioId = usuarioId;
        this.tema = tema;
        this.dificultad = dificultad;
        this.totalPreguntas = totalPreguntas;
    }

    public void marcarComoRespondido(Double puntaje) {
        this.puntaje = puntaje;
        this.estado = EstadoQuiz.RESPONDIDO;
        this.respondidoEn = LocalDateTime.now();
    }

    public boolean perteneceA(String usuarioId) {
        return this.usuarioId != null && this.usuarioId.equals(usuarioId);
    }

    public boolean estaPendiente() {
        return EstadoQuiz.PENDIENTE.equals(this.estado);
    }
}
