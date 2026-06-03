package com.quizai.service.impl;

import com.quizai.dto.ExplicacionDTO;
import com.quizai.dto.OpcionDTO;
import com.quizai.dto.PreguntaDTO;
import com.quizai.exception.RecursoNoEncontradoException;
import com.quizai.model.Pregunta;
import com.quizai.repository.PreguntaRepository;
import com.quizai.service.PreguntaService;
import com.quizai.service.ServicioIA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    private static final Logger log = LoggerFactory.getLogger(PreguntaServiceImpl.class);

    private final PreguntaRepository preguntaRepository;
    private final ServicioIA servicioIA;

    public PreguntaServiceImpl(PreguntaRepository preguntaRepository, ServicioIA servicioIA) {
        this.preguntaRepository = preguntaRepository;
        this.servicioIA = servicioIA;
    }

    @Override
    public List<PreguntaDTO> buscarPorQuiz(String quizId) {
        return preguntaRepository.findByQuizIdOrderByOrdenAsc(quizId)
                .stream()
                .map(this::mapearADTO)
                .toList();
    }

    @Override
    public Pregunta buscarPorId(String preguntaId) {
        return preguntaRepository.findById(preguntaId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Pregunta no encontrada: " + preguntaId));
    }

    @Override
    public List<Pregunta> guardarTodas(List<Pregunta> preguntas) {
        return preguntaRepository.saveAll(preguntas);
    }

    @Override
    public void eliminarPorQuiz(String quizId) {
        preguntaRepository.deleteByQuizId(quizId);
        log.info("Preguntas eliminadas para quiz: {}", quizId);
    }

    @Override
    public ExplicacionDTO explicarRespuesta(String preguntaId) {
        Pregunta pregunta = buscarPorId(preguntaId);

        if (!pregunta.tieneExplicacion()) {
            String explicacion = servicioIA.explicarRespuesta(pregunta);
            pregunta.setExplicacion(explicacion);
            preguntaRepository.save(pregunta);
        }

        var correcta = pregunta.obtenerOpcionCorrecta();
        return new ExplicacionDTO(
                pregunta.getId(),
                pregunta.getTextoPregunta(),
                correcta != null ? correcta.getTexto() : null,
                pregunta.getExplicacion()
        );
    }

    @Override
    public PreguntaDTO mapearADTO(Pregunta pregunta) {
        List<OpcionDTO> opciones = pregunta.getOpciones().stream()
                .map(o -> new OpcionDTO(o.getId(), o.getEtiqueta(), o.getTexto()))
                .toList();
        return new PreguntaDTO(pregunta.getId(), pregunta.getTextoPregunta(), opciones, pregunta.getOrden());
    }
}
