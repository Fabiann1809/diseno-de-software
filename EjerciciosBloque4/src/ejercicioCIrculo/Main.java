package ejercicioCIrculo;
public class Main {
    public static void main(String[] args) {
        // nuevo circulo
        Circle c1 = new Circle();

        // Circulo con radio específico
        Circle c2= new Circle(5.5);

        // informacion del circulo 1
        System.out.println("El radio del circulo 1 es de: " + c1.getRadius());
        System.out.println("El área del circulo 1 es de: " + String.format("%.2f",c1.getArea()));
        System.out.println("El perimetro radio del circulo 1 es de: " + String.format("%.2f",c1.getCircumference()));

        System.out.println("---------------------------");

        System.out.println("El radio del circulo 2 es de: " + c2.getRadius());
        System.out.println("El area del circulo 2 es de: " + String.format("%.2f",c2.getArea()));
        System.out.println("El perímetro del circulo 2 es de: " + String.format("%.2f",c2.getCircumference()));

        System.out.println("---------------------------");

        // Metodo setRadius
        c1.setRadius(8.5);
        System.out.println("El área del circulo 1 con un radio de 8.5 es de: " + String.format("%.2f",c1.getArea()));


    }
}
