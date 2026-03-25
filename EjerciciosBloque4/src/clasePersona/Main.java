package clasePersona;

import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);

        // Datos por teclado
        System.out.println("Ingresa datos:");
        System.out.print("Nombre: "); String nom = sc.nextLine();
        System.out.print("Edad: "); int ed = sc.nextInt();
        System.out.print("Sexo (H/M): "); char s = sc.next().toUpperCase().charAt(0);
        System.out.print("Peso: "); float p = sc.nextFloat();
        System.out.print("Altura: "); float a = sc.nextFloat();

        Sexo sexoInput = (s == 'M') ? Sexo.M : Sexo.H;

        // Crear 3 objetos
        Persona p1 = new Persona(nom, ed, sexoInput, p, a);
        Persona p2 = new Persona(nom, ed, sexoInput);
        Persona p3 = new Persona();
        p3.setNombre("Andrés"); p3.setEdad(20); p3.setPeso(70); p3.setAltura(1.8f);

        Persona[] lista = {p1, p2, p3};
        for (Persona per : lista) {
            System.out.println("\n" + per.toString());

            // IMC
            int imc = per.calcularIMC();
            if (imc == Persona.BAJO_PESO) System.out.println("Resultado: Bajo peso");
            else if (imc == Persona.PESO_IDEAL) System.out.println("Resultado: Peso ideal");
            else System.out.println("Resultado: Sobrepeso");

            // Mayor de edad
            System.out.println("Mayor de edad: " + (per.esMayorDeEdad() ? "SÍ" : "NO"));
        }
    }
}