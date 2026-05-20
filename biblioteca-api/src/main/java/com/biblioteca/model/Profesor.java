package com.biblioteca.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Profesor extends Usuario {

    private String codigoProfesor;
    private String facultad;

    public Profesor(String id, String nombre, String correo, String codigoProfesor, String facultad) {
        super(id, nombre, correo);
        this.codigoProfesor = codigoProfesor;
        this.facultad = facultad;
    }
}
