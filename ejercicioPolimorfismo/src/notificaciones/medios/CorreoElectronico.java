package notificaciones.medios;

import notificaciones.Destinatario;

public class CorreoElectronico extends MedioEnvio {

    private String asunto;

    public CorreoElectronico(String asunto) {
        this.asunto = asunto;
    }

    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }

    private void validarEmail(Destinatario destinatario) {
        String email = destinatario.getCorreoElectronico();
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Correo electrónico vacío o nulo para: " + destinatario.getNombre());
        }
    }

    @Override
    public void enviar(String mensaje, Destinatario destinatario) {
        validarEmail(destinatario);
        System.out.println("[CORREO]");
        System.out.println("  Para:    " + destinatario.getCorreoElectronico() + " (" + destinatario.getNombre() + ")");
        System.out.println("  Asunto:  " + asunto);
        System.out.println("  Mensaje: " + mensaje);
    }

    @Override
    public String toString() {
        return "CorreoElectronico{asunto='" + asunto + "'}";
    }
}
