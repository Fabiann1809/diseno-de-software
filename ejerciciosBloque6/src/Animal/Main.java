package Animal;

public class Main {
    public static void main(String[] args) {

        Cat gato = new Cat("Michi");
        Dog perro1 = new Dog("Firulais");
        Dog perro2 = new Dog("Toby");

        // Probar toString
        System.out.println(gato.toString());
        System.out.println(perro1.toString());

        //  sonidos
        System.out.print(gato.getName() + " dice: "); //
        gato.greets();

        System.out.print(perro1.getName() + " saluda normal: ");
        perro1.greets();

        System.out.print(perro1.getName() + " saluda a " + perro2.getName() + ": ");
        perro1.greets(perro2);
    }
}