package com.quizai.service.impl;

import com.quizai.config.ClienteGroq;
import com.quizai.model.Dificultad;
import com.quizai.model.Pregunta;
import com.quizai.service.ServicioIA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioIAImpl implements ServicioIA {

    private static final Logger log = LoggerFactory.getLogger(ServicioIAImpl.class);

    private static final String PROMPT_SISTEMA =
            "Eres un experto generador de quizzes educativos. " +
            "Siempre respondes con JSON válido y bien estructurado, sin texto adicional ni markdown.";

    private final ClienteGroq clienteGroq;

    public ServicioIAImpl(ClienteGroq clienteGroq) {
        this.clienteGroq = clienteGroq;
    }

    @Override
    public List<Pregunta> generarPreguntas(String tema, Dificultad dificultad, int cantidad, String quizId) {
        log.info("Generando {} preguntas sobre '{}' con dificultad {}", cantidad, tema, dificultad);
        String prompt = clienteGroq.construirPromptGeneracion(tema, dificultad, cantidad);
        String json = clienteGroq.enviarPrompt(PROMPT_SISTEMA, prompt);
        return clienteGroq.parsearPreguntas(json, quizId);
    }

    @Override
    public List<Pregunta> generarDesdeTexto(String texto, Dificultad dificultad, int cantidad, String quizId) {
        log.info("Generando {} preguntas desde texto con dificultad {}", cantidad, dificultad);
        String prompt = clienteGroq.construirPromptDesdeTexto(texto, dificultad, cantidad);
        String json = clienteGroq.enviarPrompt(PROMPT_SISTEMA, prompt);
        return clienteGroq.parsearPreguntas(json, quizId);
    }

    @Override
    public String explicarRespuesta(Pregunta pregunta) {
        log.info("Generando explicación para pregunta: {}", pregunta.getId());
        String prompt = clienteGroq.construirPromptExplicacion(pregunta);
        return clienteGroq.enviarPrompt(
                "Eres un tutor educativo que explica respuestas de forma clara y concisa.",
                prompt
        );
    }
}
