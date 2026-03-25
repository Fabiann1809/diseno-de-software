package fabricaCarros;

public class Llanta {
    private float tamanio;
    private TipoLlanta tipo;

    public Llanta(float tamanio, TipoLlanta tipo) {
        this.tamanio = tamanio;
        this.tipo = tipo;
    }

    public float getTamanio() {
        return tamanio;
    }

    public TipoLlanta getTipo() {
        return tipo;
    }

    public void setTamanio(float tamanio) {
        this.tamanio = tamanio;
    }

    public void setTipo(TipoLlanta tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Llanta{" +
                "tamanio=" + tamanio +
                ", tipo=" + tipo +
                '}';
    }
}
