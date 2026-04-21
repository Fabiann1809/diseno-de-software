package notificaciones.situaciones;

public abstract class Situacion {

    private String descripcion;

    public Situacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public abstract String generarMensaje();

    @Override
    public String toString() {
        return "Situacion{" +
               "descripcion='" + descripcion + '\'' +
               '}';
    }
}
