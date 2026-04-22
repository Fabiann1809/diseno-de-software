package notificaciones.situaciones;

public class PublicacionCalificaciones extends Situacion {

    private String materia;
    private String periodo;

    public PublicacionCalificaciones(String materia, String periodo) {
        this.materia = materia;
        this.periodo = periodo;
    }

    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }

    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }

    @Override
    public String generarMensaje() {
        return "Las calificaciones de " + materia + " del periodo " + periodo + " ya están disponibles en el portal académico.";
    }

    @Override
    public String toString() {
        return "PublicacionCalificaciones{materia='" + materia + "', periodo='" + periodo + "'}";
    }
}
