package notificaciones;

import notificaciones.medios.MedioEnvio;
import notificaciones.situaciones.Situacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Notificacion {

    private String codigo;
    private LocalDate fechaEnvio;
    private EstadoEnum estado;
    private List<Destinatario> destinatarios;
    private Situacion situacion;
    private List<MedioEnvio> mediosEnvio;

    public Notificacion(String codigo, List<Destinatario> destinatarios, Situacion situacion, List<MedioEnvio> mediosEnvio) {
        this.codigo = codigo;
        this.destinatarios = new ArrayList<>(destinatarios);
        this.situacion = situacion;
        this.mediosEnvio = new ArrayList<>(mediosEnvio);
        this.fechaEnvio = LocalDate.now();
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public LocalDate getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDate fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public EstadoEnum getEstado() { return estado; }
    public void setEstado(EstadoEnum estado) { this.estado = estado; }

    public List<Destinatario> getDestinatarios() { return destinatarios; }
    public void setDestinatarios(List<Destinatario> destinatarios) { this.destinatarios = destinatarios; }

    public Situacion getSituacion() { return situacion; }
    public void setSituacion(Situacion situacion) { this.situacion = situacion; }

    public List<MedioEnvio> getMediosEnvio() { return mediosEnvio; }
    public void setMediosEnvio(List<MedioEnvio> mediosEnvio) { this.mediosEnvio = mediosEnvio; }

    public void agregarMedio(MedioEnvio medio) {
        this.mediosEnvio.add(medio);
    }

    public void agregarDestinatario(Destinatario destinatario) {
        this.destinatarios.add(destinatario);
    }

    public void enviar() {
        System.out.println("\n========================================");
        System.out.println(" Notificación: " + codigo);
        System.out.println(" Situación:    " + situacion);
        System.out.println("----------------------------------------");
        try {
            String mensaje = situacion.generarMensaje();
            for (Destinatario destinatario : destinatarios) {
                for (MedioEnvio medio : mediosEnvio) {
                    medio.enviar(mensaje, destinatario);
                }
            }
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
               ", fechaEnvio=" + fechaEnvio +
               ", estado=" + estado +
               ", destinatarios=" + destinatarios +
               ", situacion=" + situacion +
               ", mediosEnvio=" + mediosEnvio +
               '}';
    }
}
