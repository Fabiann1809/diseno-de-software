package notificaciones.medios;

public class NotificacionApp extends MedioEnvio {

    private String tokenDispositivo;
    private String plataforma;

    public NotificacionApp(String tokenDispositivo, String plataforma) {
        super("Notificación en aplicación móvil");
        this.tokenDispositivo = tokenDispositivo;
        this.plataforma = plataforma;
    }

    public String getTokenDispositivo() { return tokenDispositivo; }
    public void setTokenDispositivo(String tokenDispositivo) { this.tokenDispositivo = tokenDispositivo; }

    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }

    public void registrarToken() {
        System.out.println("  [Token registrado: " + tokenDispositivo + " | Plataforma: " + plataforma + "]");
    }

    @Override
    public void enviar(String mensaje) {
        registrarToken();
        System.out.println("[APP MÓVIL - " + plataforma + "]");
        System.out.println("  Push:    " + mensaje);
    }

    @Override
    public String toString() {
        return "NotificacionApp{" +
               "nombre='" + getNombre() + '\'' +
               ", tokenDispositivo='" + tokenDispositivo + '\'' +
               ", plataforma='" + plataforma + '\'' +
               '}';
    }
}
