package notificaciones.medios;

import notificaciones.Destinatario;

public class NotificacionApp extends MedioEnvio {

    public NotificacionApp() {}

    private void registrarToken(Destinatario destinatario) {
        String token = destinatario.getTokenRegistro();
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Token de registro nulo o vacío para: " + destinatario.getNombre());
        }
        System.out.println("  [Token registrado: " + token + "]");
    }

    @Override
    public void enviar(String mensaje, Destinatario destinatario) {
        registrarToken(destinatario);
        System.out.println("[APP MÓVIL]");
        System.out.println("  Para:    " + destinatario.getNombre());
        System.out.println("  Push:    " + mensaje);
    }

    @Override
    public String toString() {
        return "NotificacionApp{}";
    }
}
