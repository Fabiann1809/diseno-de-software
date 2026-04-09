package circle;

public class Main {
    public static void main(String[] args) {
        //Circulo
        Circle myCircle = new Circle();
        System.out.println(myCircle);

        Circle C2 = new Circle(2,"verde");
        System.out.println(C2);

        // Cilindro
        Cylinder myCylinder = new Cylinder();
        System.out.println(myCylinder);

        Cylinder CY2 = new Cylinder(2,"Amarillo", 5);
        System.out.println(CY2

        );
    }
}
