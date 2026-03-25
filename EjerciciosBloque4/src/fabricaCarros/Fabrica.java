package fabricaCarros;
import java.util.List;
public class Fabrica {
    private List <Planta> planta;

    public Fabrica(List<Planta> planta) {
        this.planta = planta;
    }

    public List<Planta> getPlanta() {
        return planta;
    }

    public void setPlanta(List<Planta> planta) {
        this.planta = planta;
    }

    @Override
    public String toString() {
        return "Fabrica{" +
                "planta=" + planta +
                '}';
    }
}

