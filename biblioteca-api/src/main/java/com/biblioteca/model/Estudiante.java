package com.biblioteca.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Estudiante extends Usuario {

    private String codigoEstudiante;
    private String programa;

    public Estudiante(String id, String nombre, String correo, String codigoEstudiante, String programa) {
        super(id, nombre, correo);
        this.codigoEstudiante = codigoEstudiante;
        this.programa = programa;
    }
}
