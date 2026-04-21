package notificaciones.situaciones;

import java.time.LocalDate;

public class ConfirmacionInscripcion extends Situacion {

    private String evento;
    private LocalDate fecha;

    public ConfirmacionInscripcion(String evento, LocalDate fecha) {
        super("Confirmación de inscripción a evento académico");
        this.evento = evento;
        this.fecha = fecha;
    }

    public String getEvento() { return evento; }
    public void setEvento(String evento) { this.evento = evento; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    @Override
    public String generarMensaje() {
        return "Su inscripción al evento \"" + evento + "\" programado para el " + fecha + " ha sido confirmada.";
    }

    @Override
    public String toString() {
        return "ConfirmacionInscripcion{" +
               "descripcion='" + getDescripcion() + '\'' +
               ", evento='" + evento + '\'' +
               ", fecha=" + fecha +
               '}';
    }
}
