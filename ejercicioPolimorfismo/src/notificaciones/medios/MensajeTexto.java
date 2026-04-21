package notificaciones.medios;

public class MensajeTexto extends MedioEnvio {

    private String numeroCelular;
    private String operadora;

    public MensajeTexto(String numeroCelular, String operadora) {
        super("Mensaje de texto (SMS)");
        this.numeroCelular = numeroCelular;
        this.operadora = operadora;
    }

    public String getNumeroCelular() { return numeroCelular; }
    public void setNumeroCelular(String numeroCelular) { this.numeroCelular = numeroCelular; }

    public String getOperadora() { return operadora; }
    public void setOperadora(String operadora) { this.operadora = operadora; }

    public void validarNumero() {
        if (!numeroCelular.matches("\\+?[0-9]{7,15}")) {
            throw new IllegalArgumentException("Número de celular inválido: " + numeroCelular);
        }
    }

    @Override
    public void enviar(String mensaje) {
        validarNumero();
        System.out.println("[SMS]");
        System.out.println("  Número:   " + numeroCelular + " (" + operadora + ")");
        System.out.println("  Mensaje:  " + mensaje);
    }

    @Override
    public String toString() {
        return "MensajeTexto{" +
               "nombre='" + getNombre() + '\'' +
               ", numeroCelular='" + numeroCelular + '\'' +
               ", operadora='" + operadora + '\'' +
               '}';
    }
}
