package Model;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("   Bienvenido a la Editorial LibroMundo  ");
        System.out.println("==========================================");
        System.out.println();
        System.out.println("Vamos a crear un video");
        System.out.println();

        System.out.print("¿Cual es el titulo del video? ");
        String titulo = scanner.nextLine();

        System.out.print("¿Cual es el precio del video? ");
        double precio = scanner.nextDouble();

        System.out.println("¿Cual es el idioma del video?");
        System.out.println("  1. ESPAÑOL");
        System.out.println("  2. INGLES");
        System.out.println("  3. PORTUGUES");
        System.out.print("Ingrese una opcion (1-3): ");
        int opcionIdioma = scanner.nextInt();

        Idioma idioma;
        switch (opcionIdioma) {
            case 1: idioma = Idioma.ESPAÑOL;    break;
            case 2: idioma = Idioma.INGLES;     break;
            case 3: idioma = Idioma.PORTUGUES;  break;
            default:
                System.out.println("Opcion no valida, se asignara ESPAÑOL por defecto.");
                idioma = Idioma.ESPAÑOL;
        }

        System.out.print("¿Cual es la duracion del video (en horas)? ");
        float duracionHoras = scanner.nextFloat();

        Video video = new Video(titulo, precio, idioma, duracionHoras);

        System.out.println();
        System.out.println("==========================================");
        System.out.println("         Informacion del video           ");
        System.out.println("==========================================");
        System.out.println(video.toString());

        scanner.close();
    }
}