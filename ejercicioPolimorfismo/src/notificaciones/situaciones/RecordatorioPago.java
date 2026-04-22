package notificaciones.situaciones;

import java.time.LocalDate;

public class RecordatorioPago extends Situacion {

    private Double monto;
    private LocalDate fechaLimite;

    public RecordatorioPago(Double monto, LocalDate fechaLimite) {
        this.monto = monto;
        this.fechaLimite = fechaLimite;
    }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public LocalDate getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDate fechaLimite) { this.fechaLimite = fechaLimite; }

    @Override
    public String generarMensaje() {
        return "Tiene un pago pendiente de $" + String.format("%.2f", monto) +
               " por concepto de matrícula. Fecha límite: " + fechaLimite + ".";
    }

    @Override
    public String toString() {
        return "RecordatorioPago{monto=" + monto + ", fechaLimite=" + fechaLimite + '}';
    }
}
