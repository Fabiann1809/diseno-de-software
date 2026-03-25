package author;

public class Main {
    public static void main(String[] args) {
        // 1. Crear Author
        Author Autor1 = new Author("Gabriel García Márquez", "Gabo@correo.com", 'm');

        // 2. Crear Book
        Book miLibro = new Book("Cien años de soledad", Autor1, 2000, 10);

        // 3. Probar los métodos
        System.out.println(miLibro.toString());

        // datos del autor desde el libro
        System.out.println("El autor del libro es: " + miLibro.getAuthor().getName());
        System.out.println("El email del autor es: " + miLibro.getAuthor().getEmail());
    }

}
