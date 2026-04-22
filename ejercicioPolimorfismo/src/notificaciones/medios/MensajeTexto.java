package notificaciones.medios;

import notificaciones.Destinatario;

public class MensajeTexto extends MedioEnvio {

    public MensajeTexto() {}

    private void validarNumero(Destinatario destinatario) {
        String numero = destinatario.getNumeroCelular();
        if (numero == null || numero.isEmpty()) {
            throw new IllegalArgumentException("Número celular vacío o nulo para: " + destinatario.getNombre());
        }
    }

    @Override
    public void enviar(String mensaje, Destinatario destinatario) {
        validarNumero(destinatario);
        System.out.println("[SMS]");
        System.out.println("  Número:   " + destinatario.getNumeroCelular() + " (" + destinatario.getNombre() + ")");
        System.out.println("  Mensaje:  " + mensaje);
    }

    @Override
    public String toString() {
        return "MensajeTexto{}";
    }
}
