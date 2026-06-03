package com.quizai.controller;

import com.quizai.dto.GenerarDesdeTextoRequest;
import com.quizai.dto.GenerarQuizRequest;
import com.quizai.dto.QuizDTO;
import com.quizai.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
@Tag(name = "Quizzes", description = "Generación y gestión de quizzes")
@SecurityRequirement(name = "bearerAuth")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/generar")
    @Operation(summary = "Generar quiz desde un tema libre")
    public ResponseEntity<QuizDTO> generar(@Valid @RequestBody GenerarQuizRequest request,
                                            Authentication auth) {
        return ResponseEntity.status(HttpStatus.CREATED).body(quizService.generar(auth.getName(), request));
    }

    @PostMapping("/generar/texto")
    @Operation(summary = "Generar quiz desde un texto pegado")
    public ResponseEntity<QuizDTO> generarDesdeTexto(@Valid @RequestBody GenerarDesdeTextoRequest request,
                                                      Authentication auth) {
        return ResponseEntity.status(HttpStatus.CREATED).body(quizService.generarDesdeTexto(auth.getName(), request));
    }

    @GetMapping
    @Operation(summary = "Obtener historial de quizzes del usuario")
    public ResponseEntity<List<QuizDTO>> obtenerHistorial(Authentication auth) {
        return ResponseEntity.ok(quizService.buscarPorUsuario(auth.getName()));
    }

    @GetMapping("/{quizId}")
    @Operation(summary = "Obtener un quiz con sus preguntas")
    public ResponseEntity<QuizDTO> obtenerPorId(@PathVariable String quizId, Authentication auth) {
        return ResponseEntity.ok(quizService.buscarPorId(quizId, auth.getName()));
    }

    @DeleteMapping("/{quizId}")
    @Operation(summary = "Eliminar un quiz del historial")
    public ResponseEntity<Void> eliminar(@PathVariable String quizId, Authentication auth) {
        quizService.eliminar(quizId, auth.getName());
        return ResponseEntity.noContent().build();
    }
}
