package com.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private String id;
    private String nombre;
    private String correo;
    private String tipoUsuario;

    // Campos Estudiante
    private String codigoEstudiante;
    private String programa;

    // Campos Profesor
    private String codigoProfesor;
    private String facultad;

    // Campos Bibliotecario
    private String codigoEmpleado;
    private String turno;
}
