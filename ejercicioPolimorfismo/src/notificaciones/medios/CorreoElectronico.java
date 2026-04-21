package notificaciones.medios;

import java.util.List;

public class CorreoElectronico extends MedioEnvio {

    private String emailDestinatario;
    private String asunto;
    private List<String> adjuntos;

    public CorreoElectronico(String emailDestinatario, String asunto, List<String> adjuntos) {
        super("Correo electrónico");
        this.emailDestinatario = emailDestinatario;
        this.asunto = asunto;
        this.adjuntos = adjuntos;
    }

    public String getEmailDestinatario() { return emailDestinatario; }
    public void setEmailDestinatario(String emailDestinatario) { this.emailDestinatario = emailDestinatario; }

    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }

    public List<String> getAdjuntos() { return adjuntos; }
    public void setAdjuntos(List<String> adjuntos) { this.adjuntos = adjuntos; }

    public void validarEmail() {
        if (!emailDestinatario.contains("@") || !emailDestinatario.contains(".")) {
            throw new IllegalArgumentException("Dirección de correo inválida: " + emailDestinatario);
        }
    }

    @Override
    public void enviar(String mensaje) {
        validarEmail();
        System.out.println("[CORREO]");
        System.out.println("  Para:    " + emailDestinatario);
        System.out.println("  Asunto:  " + asunto);
        System.out.println("  Mensaje: " + mensaje);
        if (!adjuntos.isEmpty()) {
            System.out.println("  Adjuntos: " + adjuntos);
        }
    }

    @Override
    public String toString() {
        return "CorreoElectronico{" +
               "nombre='" + getNombre() + '\'' +
               ", emailDestinatario='" + emailDestinatario + '\'' +
               ", asunto='" + asunto + '\'' +
               ", adjuntos=" + adjuntos +
               '}';
    }
}
