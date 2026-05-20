package com.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ejemplares")
public class Ejemplar {

    @Id
    private String id;

    private String codigoEjemplar;
    private String estado;
    private String ubicacion;
    private String libroId;

    public void marcarPrestado() {
        this.estado = "PRESTADO";
    }

    public void marcarDisponible() {
        this.estado = "DISPONIBLE";
    }
}
