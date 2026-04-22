package notificaciones;

public class Destinatario {

    private String nombre;
    private String correoElectronico;
    private String numeroCelular;
    private String tokenRegistro;

    public Destinatario(String nombre, String correoElectronico, String numeroCelular, String tokenRegistro) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.numeroCelular = numeroCelular;
        this.tokenRegistro = tokenRegistro;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getNumeroCelular() { return numeroCelular; }
    public void setNumeroCelular(String numeroCelular) { this.numeroCelular = numeroCelular; }

    public String getTokenRegistro() { return tokenRegistro; }
    public void setTokenRegistro(String tokenRegistro) { this.tokenRegistro = tokenRegistro; }

    @Override
    public String toString() {
        return "Destinatario{" +
               "nombre='" + nombre + '\'' +
               ", correoElectronico='" + correoElectronico + '\'' +
               ", numeroCelular='" + numeroCelular + '\'' +
               ", tokenRegistro='" + tokenRegistro + '\'' +
               '}';
    }
}
