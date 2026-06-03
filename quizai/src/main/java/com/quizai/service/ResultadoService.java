package com.quizai.service;

import com.quizai.dto.RespuestaRequest;
import com.quizai.dto.ResultadoDTO;

import java.util.List;

public interface ResultadoService {
    ResultadoDTO enviarRespuestas(String quizId, String usuarioId, List<RespuestaRequest> respuestas);
    ResultadoDTO buscarPorQuiz(String quizId, String usuarioId);
}
