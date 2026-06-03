package com.quizai.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    private static final Logger log = LoggerFactory.getLogger(ManejadorGlobalExcepciones.class);

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<RespuestaError> manejarNoEncontrado(RecursoNoEncontradoException ex) {
        log.warn("Recurso no encontrado: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new RespuestaError(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(NoAutorizadoException.class)
    public ResponseEntity<RespuestaError> manejarNoAutorizado(NoAutorizadoException ex) {
        log.warn("Acceso no autorizado: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new RespuestaError(HttpStatus.FORBIDDEN.value(), ex.getMessage()));
    }

    @ExceptionHandler(ConflictoException.class)
    public ResponseEntity<RespuestaError> manejarConflicto(ConflictoException ex) {
        log.warn("Conflicto: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new RespuestaError(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }

    @ExceptionHandler(ServicioIAException.class)
    public ResponseEntity<RespuestaError> manejarServicioIA(ServicioIAException ex) {
        log.error("Error en servicio IA: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new RespuestaError(HttpStatus.SERVICE_UNAVAILABLE.value(),
                        "El servicio de IA no está disponible en este momento. Intenta de nuevo más tarde."));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespuestaErrorValidacion> manejarValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String campo = ((FieldError) error).getField();
            errores.put(campo, error.getDefaultMessage());
        });
        log.warn("Error de validación: {}", errores);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new RespuestaErrorValidacion(HttpStatus.BAD_REQUEST.value(), "Error de validación", errores));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaError> manejarGeneral(Exception ex) {
        log.error("Error inesperado", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RespuestaError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error interno del servidor"));
    }

    public record RespuestaError(int estado, String mensaje) {
        public LocalDateTime timestamp() { return LocalDateTime.now(); }
    }

    public record RespuestaErrorValidacion(int estado, String mensaje, Map<String, String> errores) {}
}
