package com.biblioteca.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Bibliotecario extends Usuario {

    private String codigoEmpleado;
    private String turno;

    public Bibliotecario(String id, String nombre, String correo, String codigoEmpleado, String turno) {
        super(id, nombre, correo);
        this.codigoEmpleado = codigoEmpleado;
        this.turno = turno;
    }
}
