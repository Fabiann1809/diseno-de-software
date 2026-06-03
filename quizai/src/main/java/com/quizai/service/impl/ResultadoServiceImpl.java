package com.quizai.service.impl;

import com.quizai.dto.DetalleRespuestaDTO;
import com.quizai.dto.RespuestaRequest;
import com.quizai.dto.ResultadoDTO;
import com.quizai.exception.ConflictoException;
import com.quizai.exception.NoAutorizadoException;
import com.quizai.exception.RecursoNoEncontradoException;
import com.quizai.model.Pregunta;
import com.quizai.model.Quiz;
import com.quizai.model.Respuesta;
import com.quizai.model.Resultado;
import com.quizai.repository.PreguntaRepository;
import com.quizai.repository.QuizRepository;
import com.quizai.repository.ResultadoRepository;
import com.quizai.service.ResultadoService;
import com.quizai.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResultadoServiceImpl implements ResultadoService {

    private static final Logger log = LoggerFactory.getLogger(ResultadoServiceImpl.class);

    private final ResultadoRepository resultadoRepository;
    private final PreguntaRepository preguntaRepository;
    private final QuizRepository quizRepository;
    private final UsuarioService usuarioService;

    public ResultadoServiceImpl(ResultadoRepository resultadoRepository,
                                PreguntaRepository preguntaRepository,
                                QuizRepository quizRepository,
                                UsuarioService usuarioService) {
        this.resultadoRepository = resultadoRepository;
        this.preguntaRepository = preguntaRepository;
        this.quizRepository = quizRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public ResultadoDTO enviarRespuestas(String quizId, String usuarioId, List<RespuestaRequest> respuestasRequest) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Quiz no encontrado: " + quizId));

        if (!quiz.perteneceA(usuarioId)) {
            throw new NoAutorizadoException("No tienes permiso para responder este quiz");
        }
        if (!quiz.estaPendiente()) {
            throw new ConflictoException("Este quiz ya fue respondido");
        }
        if (resultadoRepository.existsByQuizId(quizId)) {
            throw new ConflictoException("Ya existe un resultado para este quiz");
        }

        List<Pregunta> preguntas = preguntaRepository.findByQuizIdOrderByOrdenAsc(quizId);
        Map<String, String> mapaCorrectas = preguntas.stream()
                .collect(Collectors.toMap(Pregunta::getId, Pregunta::getOpcionCorrectaId));

        Resultado resultado = new Resultado(quizId, usuarioId);

        for (RespuestaRequest req : respuestasRequest) {
            Respuesta respuesta = new Respuesta(resultado.getId(), req.preguntaId(), req.opcionSeleccionadaId());
            respuesta.evaluar(mapaCorrectas.get(req.preguntaId()));
            resultado.agregarRespuesta(respuesta);
        }

        resultado.calcular();
        Resultado guardado = resultadoRepository.save(resultado);

        quiz.marcarComoRespondido(guardado.getPorcentajePuntaje());
        quizRepository.save(quiz);

        usuarioService.actualizarEstadisticas(usuarioId, guardado.getPorcentajePuntaje(), quiz.getTema());
        log.info("Quiz {} respondido por usuario {}. Puntaje: {}%", quizId, usuarioId, guardado.getPorcentajePuntaje());

        return mapearADTO(guardado, mapaCorrectas);
    }

    @Override
    public ResultadoDTO buscarPorQuiz(String quizId, String usuarioId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Quiz no encontrado: " + quizId));

        if (!quiz.perteneceA(usuarioId)) {
            throw new NoAutorizadoException("No tienes permiso para ver este resultado");
        }

        Resultado resultado = resultadoRepository.findByQuizId(quizId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Resultado no encontrado para el quiz: " + quizId));

        List<Pregunta> preguntas = preguntaRepository.findByQuizIdOrderByOrdenAsc(quizId);
        Map<String, String> mapaCorrectas = preguntas.stream()
                .collect(Collectors.toMap(Pregunta::getId, Pregunta::getOpcionCorrectaId));

        return mapearADTO(resultado, mapaCorrectas);
    }

    private ResultadoDTO mapearADTO(Resultado resultado, Map<String, String> mapaCorrectas) {
        List<DetalleRespuestaDTO> detalles = resultado.getRespuestas().stream()
                .map(r -> new DetalleRespuestaDTO(
                        r.getPreguntaId(),
                        r.getOpcionSeleccionadaId(),
                        mapaCorrectas.getOrDefault(r.getPreguntaId(), null),
                        r.isEsCorrecta()
                ))
                .toList();

        return new ResultadoDTO(
                resultado.getId(),
                resultado.getQuizId(),
                resultado.getTotalCorrectas(),
                resultado.getTotalIncorrectas(),
                resultado.getPorcentajePuntaje(),
                resultado.getEnviadoEn(),
                detalles
        );
    }
}
