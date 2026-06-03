package com.quizai.service;

import com.quizai.model.Dificultad;
import com.quizai.model.Pregunta;

import java.util.List;

public interface ServicioIA {
    List<Pregunta> generarPreguntas(String tema, Dificultad dificultad, int cantidad, String quizId);
    List<Pregunta> generarDesdeTexto(String texto, Dificultad dificultad, int cantidad, String quizId);
    String explicarRespuesta(Pregunta pregunta);
}
