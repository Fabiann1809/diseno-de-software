package com.quizai.controller;

import com.quizai.dto.EnviarRespuestasRequest;
import com.quizai.dto.ResultadoDTO;
import com.quizai.service.ResultadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quizzes/{quizId}/resultados")
@Tag(name = "Resultados", description = "Envío y consulta de resultados de quizzes")
@SecurityRequirement(name = "bearerAuth")
public class ResultadoController {

    private final ResultadoService resultadoService;

    public ResultadoController(ResultadoService resultadoService) {
        this.resultadoService = resultadoService;
    }

    @PostMapping
    @Operation(summary = "Enviar respuestas y obtener resultado del quiz")
    public ResponseEntity<ResultadoDTO> enviarRespuestas(@PathVariable String quizId,
                                                          @Valid @RequestBody EnviarRespuestasRequest request,
                                                          Authentication auth) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(resultadoService.enviarRespuestas(quizId, auth.getName(), request.respuestas()));
    }

    @GetMapping
    @Operation(summary = "Consultar resultado de un quiz respondido")
    public ResponseEntity<ResultadoDTO> obtenerResultado(@PathVariable String quizId,
                                                          Authentication auth) {
        return ResponseEntity.ok(resultadoService.buscarPorQuiz(quizId, auth.getName()));
    }
}
