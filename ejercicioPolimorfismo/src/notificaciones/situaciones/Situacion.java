package notificaciones.situaciones;

public abstract class Situacion {

    public abstract String generarMensaje();

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
