package Model;

public class Video extends Publicacion{
    private Idioma idioma;
    private float duracionHoras;

    public Video() {

    }

    public Video(String titulo, double precio, Idioma idioma, float duracionHoras) {
        super(titulo, precio);
        this.idioma = idioma;
        this.duracionHoras = duracionHoras;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public float getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(float duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    @Override
    public String toString() {
        return "Video{" +
                "idioma=" + idioma +
                ", duracionHoras=" + duracionHoras +
                ", titulo=" + getTitulo() +
                ", precio=" + getPrecio() +
                '}';
    }
}
