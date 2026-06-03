package com.quizai.exception;

public class ServicioIAException extends RuntimeException {
    public ServicioIAException(String mensaje) {
        super(mensaje);
    }

    public ServicioIAException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
