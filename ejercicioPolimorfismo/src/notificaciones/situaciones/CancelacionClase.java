package notificaciones.situaciones;

public class CancelacionClase extends Situacion {

    private String docente;
    private String salon;

    public CancelacionClase(String docente, String salon) {
        this.docente = docente;
        this.salon = salon;
    }

    public String getDocente() { return docente; }
    public void setDocente(String docente) { this.docente = docente; }

    public String getSalon() { return salon; }
    public void setSalon(String salon) { this.salon = salon; }

    @Override
    public String generarMensaje() {
        return "La clase del docente " + docente + " programada en el salón " + salon + " ha sido cancelada.";
    }

    @Override
    public String toString() {
        return "CancelacionClase{docente='" + docente + "', salon='" + salon + "'}";
    }
}
