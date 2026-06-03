package com.quizai.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;

    private String nombre;

    @Indexed(unique = true)
    private String correo;

    private String contrasena;

    private int totalQuizzes;
    private int totalRespondidos;
    private double promedioPuntaje;
    private List<String> temasFrecuentes = new ArrayList<>();
    private LocalDateTime creadoEn = LocalDateTime.now();

    public Usuario(String nombre, String correo, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public void actualizarEstadisticas(double puntaje, String tema) {
        this.totalRespondidos++;
        this.promedioPuntaje = ((this.promedioPuntaje * (this.totalRespondidos - 1)) + puntaje) / this.totalRespondidos;

        if (tema != null && !tema.isBlank()) {
            this.temasFrecuentes.remove(tema);
            this.temasFrecuentes.add(0, tema);
            if (this.temasFrecuentes.size() > 10) {
                this.temasFrecuentes = this.temasFrecuentes.subList(0, 10);
            }
        }
    }

    public boolean perteneceA(String id) {
        return this.id != null && this.id.equals(id);
    }
}
