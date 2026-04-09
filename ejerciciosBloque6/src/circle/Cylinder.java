package circle;

public class Cylinder extends Circle {
    private double height;

    public Cylinder() {
        this.height = 1.0;
    }

    public Cylinder(double radius, double height) {
        super(radius);
        this.height = height;
    }

    public Cylinder(double radius, String color, double height) {
        super(radius, color);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    public double getVolume(){
        return super.getArea() * height;
    }

    @Override
    public String toString() {
        return "Datos del cilindro: Tiene un radio de: " + getRadius() +
                " Y tiene un área de: " + getArea() + " el cilindro es de color: " + getColor()
                + " Y tiene un volúmen de: " + getVolume();
    }
}
