package fabricaCarros;

import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {

        // Creación LLanta para planta 1
        Llanta llanta1 = new Llanta(20.50f, TipoLlanta.NIEVE);

        // Creación chasis para planta 1
        Chasis chasis1 = new Chasis(54f, MaterialChasis.ALUMINIO);

        //Colores planta 1
        ArrayList<String> colores1 = new ArrayList<>();
        colores1.add("Verde");
        colores1.add("Rojo");
        colores1.add("Blanco");

        // Planta 1
        Planta planta1 = new Planta(llanta1, chasis1, colores1);


        //-------------------------------------------------------------------

        // creación llanta para planta 2
        Llanta llanta2 = new Llanta(17.5f, TipoLlanta.RADIAL);

        // creación chasis para planta 2
        Chasis chasis2 = new Chasis(40f, MaterialChasis.ACERO);

        // colores planta 2
        ArrayList<String> colores2 = new ArrayList<>();
        colores2.add("Negro");
        colores2.add("Azul");
        colores2.add("Gris");

        // PLanta 2
        Planta planta2= new Planta(llanta2, chasis2, colores2);

        // Fabricar carros planta 1
        for (int i=0; i<100; i++) {
            Carro carro = planta1.fabricar();
            System.out.println("Planta1: " + carro);
        }

        //Fabricar carros planta 2
        for (int i= 0; i<100; i++) {
            Carro carro2 = planta2.fabricar();
            System.out.println("Planta2: "+ carro2);
        }

    }
}

