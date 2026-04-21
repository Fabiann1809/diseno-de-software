package notificaciones.medios;

public abstract class MedioEnvio {

    private String nombre;

    public MedioEnvio(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public abstract void enviar(String mensaje);

    @Override
    public String toString() {
        return "MedioEnvio{" +
               "nombre='" + nombre + '\'' +
               '}';
    }
}
