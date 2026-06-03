package com.quizai.service;

import com.quizai.dto.ExplicacionDTO;
import com.quizai.dto.PreguntaDTO;
import com.quizai.model.Pregunta;

import java.util.List;

public interface PreguntaService {
    List<PreguntaDTO> buscarPorQuiz(String quizId);
    Pregunta buscarPorId(String preguntaId);
    List<Pregunta> guardarTodas(List<Pregunta> preguntas);
    void eliminarPorQuiz(String quizId);
    ExplicacionDTO explicarRespuesta(String preguntaId);
    PreguntaDTO mapearADTO(Pregunta pregunta);
}
