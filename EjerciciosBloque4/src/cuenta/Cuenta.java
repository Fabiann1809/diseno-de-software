package cuenta;

public class Cuenta {
    private String titular;
    private double cantidad;

    public Cuenta(String titular) {
        this.titular = titular;
        this.cantidad = 0.0;
    }

    public Cuenta(String titular, double cantidad) {
        this.titular = titular;
        this.cantidad = cantidad;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public void ingresar(double cantidadAIngresar) {
        if (cantidadAIngresar > 0) {
            this.cantidad += cantidadAIngresar;
        }
    }

    public void retirar(double cantidadARetirar) {
        this.cantidad -= cantidadARetirar;
        if (this.cantidad < 0) {
            this.cantidad = 0;
        }
    }
    @Override
    public String toString() {
        return "Cuenta [titular=" + titular + ", cantidad=" + cantidad + "]";
    }
}
