package com.quizai.service.impl;

import com.quizai.dto.GenerarDesdeTextoRequest;
import com.quizai.dto.GenerarQuizRequest;
import com.quizai.dto.QuizDTO;
import com.quizai.exception.RecursoNoEncontradoException;
import com.quizai.exception.NoAutorizadoException;
import com.quizai.model.Pregunta;
import com.quizai.model.Quiz;
import com.quizai.repository.QuizRepository;
import com.quizai.service.PreguntaService;
import com.quizai.service.QuizService;
import com.quizai.service.ServicioIA;
import com.quizai.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private static final Logger log = LoggerFactory.getLogger(QuizServiceImpl.class);

    private final QuizRepository quizRepository;
    private final PreguntaService preguntaService;
    private final ServicioIA servicioIA;
    private final UsuarioService usuarioService;

    public QuizServiceImpl(QuizRepository quizRepository,
                           PreguntaService preguntaService,
                           ServicioIA servicioIA,
                           UsuarioService usuarioService) {
        this.quizRepository = quizRepository;
        this.preguntaService = preguntaService;
        this.servicioIA = servicioIA;
        this.usuarioService = usuarioService;
    }

    @Override
    public QuizDTO generar(String usuarioId, GenerarQuizRequest request) {
        Quiz quiz = new Quiz(usuarioId, request.tema(), request.dificultad(), request.cantidad());
        Quiz guardado = quizRepository.save(quiz);

        List<Pregunta> preguntas = servicioIA.generarPreguntas(
                request.tema(), request.dificultad(), request.cantidad(), guardado.getId());
        preguntaService.guardarTodas(preguntas);

        usuarioService.incrementarTotalQuizzes(usuarioId);
        log.info("Quiz generado: {} para usuario: {}", guardado.getId(), usuarioId);
        return mapearADTO(guardado, preguntas);
    }

    @Override
    public QuizDTO generarDesdeTexto(String usuarioId, GenerarDesdeTextoRequest request) {
        Quiz quiz = new Quiz(usuarioId, "Texto personalizado", request.dificultad(), request.cantidad());
        quiz.setTextoFuente(request.texto());
        Quiz guardado = quizRepository.save(quiz);

        List<Pregunta> preguntas = servicioIA.generarDesdeTexto(
                request.texto(), request.dificultad(), request.cantidad(), guardado.getId());
        preguntaService.guardarTodas(preguntas);

        usuarioService.incrementarTotalQuizzes(usuarioId);
        log.info("Quiz generado desde texto: {} para usuario: {}", guardado.getId(), usuarioId);
        return mapearADTO(guardado, preguntas);
    }

    @Override
    public List<QuizDTO> buscarPorUsuario(String usuarioId) {
        return quizRepository.findByUsuarioId(usuarioId).stream()
                .map(q -> mapearADTO(q, List.of()))
                .toList();
    }

    @Override
    public QuizDTO buscarPorId(String quizId, String usuarioId) {
        Quiz quiz = obtenerQuiz(quizId);
        validarPropiedad(quiz, usuarioId);
        List<com.quizai.dto.PreguntaDTO> preguntas = preguntaService.buscarPorQuiz(quizId);
        return new QuizDTO(
                quiz.getId(), quiz.getTema(), quiz.getDificultad(),
                quiz.getTotalPreguntas(), quiz.getEstado(), quiz.getPuntaje(),
                quiz.getCreadoEn(), preguntas
        );
    }

    @Override
    public void eliminar(String quizId, String usuarioId) {
        Quiz quiz = obtenerQuiz(quizId);
        validarPropiedad(quiz, usuarioId);
        preguntaService.eliminarPorQuiz(quizId);
        quizRepository.deleteById(quizId);
        log.info("Quiz eliminado: {} por usuario: {}", quizId, usuarioId);
    }

    @Override
    public Quiz obtenerQuiz(String quizId) {
        return quizRepository.findById(quizId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Quiz no encontrado: " + quizId));
    }

    @Override
    public void validarPropiedad(Quiz quiz, String usuarioId) {
        if (!quiz.perteneceA(usuarioId)) {
            throw new NoAutorizadoException("No tienes permiso para acceder a este quiz");
        }
    }

    private QuizDTO mapearADTO(Quiz quiz, List<Pregunta> preguntas) {
        var preguntasDTO = preguntas.stream().map(preguntaService::mapearADTO).toList();
        return new QuizDTO(
                quiz.getId(), quiz.getTema(), quiz.getDificultad(),
                quiz.getTotalPreguntas(), quiz.getEstado(), quiz.getPuntaje(),
                quiz.getCreadoEn(), preguntasDTO
        );
    }
}
