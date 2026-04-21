package notificaciones.situaciones;

import java.time.LocalDate;

public class RecordatorioPago extends Situacion {

    private double monto;
    private LocalDate fechaLimite;

    public RecordatorioPago(double monto, LocalDate fechaLimite) {
        super("Recordatorio de pago de matrícula");
        this.monto = monto;
        this.fechaLimite = fechaLimite;
    }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public LocalDate getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDate fechaLimite) { this.fechaLimite = fechaLimite; }

    @Override
    public String generarMensaje() {
        return "Tiene un pago pendiente de $" + String.format("%.2f", monto) +
               " por concepto de matrícula. Fecha límite: " + fechaLimite + ".";
    }

    @Override
    public String toString() {
        return "RecordatorioPago{" +
               "descripcion='" + getDescripcion() + '\'' +
               ", monto=" + monto +
               ", fechaLimite=" + fechaLimite +
               '}';
    }
}
