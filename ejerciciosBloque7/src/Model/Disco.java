package Model;

public class Disco extends Publicacion {
    private float duracionMinutos;

    public Disco() {
    }

    public Disco(String titulo, double precio, float duracionMinutos) {
        super(titulo, precio);
        this.duracionMinutos = duracionMinutos;
    }

    public float getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(float duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "duracionMinutos=" + duracionMinutos +
                ", titulo=" + getTitulo() +
                ", precio=" + getPrecio() +
                '}';
    }
}
