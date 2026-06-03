package com.quizai.controller;

import com.quizai.dto.ExplicacionDTO;
import com.quizai.dto.PreguntaDTO;
import com.quizai.service.PreguntaService;
import com.quizai.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes/{quizId}/preguntas")
@Tag(name = "Preguntas", description = "Preguntas de un quiz y explicaciones de respuestas")
@SecurityRequirement(name = "bearerAuth")
public class PreguntaController {

    private final PreguntaService preguntaService;
    private final QuizService quizService;

    public PreguntaController(PreguntaService preguntaService, QuizService quizService) {
        this.preguntaService = preguntaService;
        this.quizService = quizService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las preguntas de un quiz")
    public ResponseEntity<List<PreguntaDTO>> obtenerPorQuiz(@PathVariable String quizId,
                                                             Authentication auth) {
        quizService.validarPropiedad(quizService.obtenerQuiz(quizId), auth.getName());
        return ResponseEntity.ok(preguntaService.buscarPorQuiz(quizId));
    }

    @GetMapping("/{preguntaId}/explicacion")
    @Operation(summary = "Solicitar explicación de la respuesta correcta")
    public ResponseEntity<ExplicacionDTO> explicar(@PathVariable String quizId,
                                                    @PathVariable String preguntaId,
                                                    Authentication auth) {
        quizService.validarPropiedad(quizService.obtenerQuiz(quizId), auth.getName());
        return ResponseEntity.ok(preguntaService.explicarRespuesta(preguntaId));
    }
}
