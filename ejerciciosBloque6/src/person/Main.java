package person;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Person p1 = new Person("Alejandra Torres", "Calle 10 #5-20");
        System.out.println("\n" + p1.toString());

        Student s1 = new Student("Carlos Ruiz", "Av. Santander 45", "Ingeniería de Sistemas", 2026, 1500.50);
        System.out.println("\n" + s1.toString());

        Staff st1 = new Staff("Dra. Maria Lopez", "Carrera 8 #12-30", "Escuela de Tecnología", 2500.0);
        System.out.println("\n" + st1.toString());

        LocalDate fechaNac = LocalDate.of(1950, 4, 4);
        Person miP = new Person("Fabian","Calle 11 #5-20", fechaNac , "Fabi@gmail.com");
        System.out.println(miP);

    }
}