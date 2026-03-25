package fabricaCarros;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Planta {
    private Llanta tipoLlanta;
    private Chasis tipoChasis;
    private ArrayList<String> colores;

    public Planta(Llanta tipoLlanta, Chasis tipoChasis, ArrayList<String> colores) {
        this.tipoLlanta = tipoLlanta;
        this.tipoChasis = tipoChasis;
        this.colores = colores;
    }

    public Llanta getTipoLlanta() {
        return tipoLlanta;
    }

    public Chasis getTipoChasis() {
        return tipoChasis;
    }

    public ArrayList<String> getColores() {
        return colores;
    }

    public void setTipoLlanta(Llanta tipoLlanta) {
        this.tipoLlanta = tipoLlanta;
    }

    public void setTipoChasis(Chasis tipoChasis) {
        this.tipoChasis = tipoChasis;
    }

    public void setColores(ArrayList<String> colores) {
        this.colores = colores;
    }
    public Carro fabricar() {
        // Elegir un color aleatorio de la lista
        Random random = new Random();
        int indice = random.nextInt(colores.size());
        String colorElegido = colores.get(indice);

        // Crear la lista de llantas
        List<Llanta> llantas = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            llantas.add(this.tipoLlanta);
        }
        //Crear y retornar el carro
        return new Carro(colorElegido, this.tipoChasis, llantas);
    }
                
    @Override
    public String toString() {
        return "Planta{" +
                "tipoLlanta=" + tipoLlanta +
                ", tipoChasis=" + tipoChasis +
                ", colores=" + colores +
                '}';
    }
}
