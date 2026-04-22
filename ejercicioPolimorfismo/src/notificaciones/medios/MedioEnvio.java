package notificaciones.medios;

import notificaciones.Destinatario;

public abstract class MedioEnvio {

    public abstract void enviar(String mensaje, Destinatario destinatario);

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
