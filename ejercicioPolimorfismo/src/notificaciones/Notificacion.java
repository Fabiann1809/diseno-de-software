package notificaciones;

import notificaciones.medios.MedioEnvio;
import notificaciones.situaciones.Situacion;

import java.time.LocalDate;

public class Notificacion {

    private String codigo;
    private String destinatario;
    private LocalDate fechaEnvio;
    private EstadoEnum estado;
    private Situacion situacion;
    private MedioEnvio medioEnvio;

    public Notificacion(String codigo, String destinatario, Situacion situacion, MedioEnvio medioEnvio) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.fechaEnvio = LocalDate.now();
        this.estado = EstadoEnum.PENDIENTE;
        this.situacion = situacion;
        this.medioEnvio = medioEnvio;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public LocalDate getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDate fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public EstadoEnum getEstado() { return estado; }
    public void setEstado(EstadoEnum estado) { this.estado = estado; }

    public Situacion getSituacion() { return situacion; }
    public void setSituacion(Situacion situacion) { this.situacion = situacion; }

    public MedioEnvio getMedioEnvio() { return medioEnvio; }
    public void setMedioEnvio(MedioEnvio medioEnvio) { this.medioEnvio = medioEnvio; }

    public void enviar() {
        System.out.println("\n========================================");
        System.out.println(" Notificación: " + codigo);
        System.out.println(" Destinatario: " + destinatario);
        System.out.println(" Situación:    " + situacion.getDescripcion());
        System.out.println(" Medio:        " + medioEnvio.getNombre());
        System.out.println("----------------------------------------");
        try {
            String mensaje = situacion.generarMensaje();
            medioEnvio.enviar(mensaje);
            this.estado = EstadoEnum.ENVIADO;
        } catch (Exception e) {
            this.estado = EstadoEnum.FALLIDO;
            System.out.println("[ERROR] " + e.getMessage());
        }
        System.out.println(" Estado final: " + estado);
        System.out.println("========================================");
    }

    @Override
    public String toString() {
        return "Notificacion{" +
               "codigo='" + codigo + '\'' +
               ", destinatario='" + destinatario + '\'' +
               ", fechaEnvio=" + fechaEnvio +
               ", estado=" + estado +
               ", situacion=" + situacion.getDescripcion() +
               ", medioEnvio=" + medioEnvio.getNombre() +
               '}';
    }
}
