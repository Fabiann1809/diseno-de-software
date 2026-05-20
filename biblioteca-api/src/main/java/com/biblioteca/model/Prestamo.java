package com.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "prestamos")
public class Prestamo {

    @Id
    private String id;

    private String usuarioId;
    private String ejemplarId;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionEsperada;
    private LocalDate fechaDevolucionReal;
    private String estado; // ACTIVO, DEVUELTO, CANCELADO

    // Calcula mora a razon de 500 pesos por dia de retraso
    public double calcularMora() {
        LocalDate referencia = fechaDevolucionReal != null ? fechaDevolucionReal : LocalDate.now();
        if (fechaDevolucionEsperada != null && referencia.isAfter(fechaDevolucionEsperada)) {
            long diasRetraso = ChronoUnit.DAYS.between(fechaDevolucionEsperada, referencia);
            return diasRetraso * 500.0;
        }
        return 0.0;
    }

    public void cancelarPrestamo() {
        this.estado = "CANCELADO";
    }
}
