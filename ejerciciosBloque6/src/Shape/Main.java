package Shape;

public class Main {
    public static void main(String[] args) {

        //  un Círculo
        Circle c1 = new Circle(5.5,Color.AZUL, false);
        System.out.println("\n[Círculo]");
        System.out.println(c1.toString());
        System.out.printf("Área: %.2f | Perímetro: %.2f%n", c1.getArea(), c1.getPerimeter());

        //  un Rectángulo
        Rectangle r1 = new Rectangle(4.0, 8.0, Color.ROJO, true);
        System.out.println("\n[Rectángulo]");
        System.out.println(r1.toString());
        System.out.printf("Área: %.2f | Perímetro: %.2f%n", r1.getArea(), r1.getPerimeter());

        //  un Cuadrado
        Square s1 = new Square(10.0, Color.AMARILLO, true);
        System.out.println("\n[Cuadrado]");
        System.out.println(s1.toString()); // Verás que dice Square[Rectangle[Shape[...]]]
        System.out.printf("Área: %.2f | Perímetro: %.2f%n", s1.getArea(), s1.getPerimeter());

        // lados Cuadrado

        s1.setSide(20.0);
        System.out.println("Nuevo Side: " + s1.getSide());
        System.out.println("Ancho: " + s1.getWidth());
        System.out.println("Largo: " + s1.getLength());

        // nueva área
        System.out.println("Nueva Área del Cuadrado: " + s1.getArea());

        //Nuevo rectangulo
        Rectangle miR1 = new Rectangle(5,4, Color.VIOLETA, true);
        System.out.println(miR1);

    }
}