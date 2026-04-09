package Shape;

public class Square extends Rectangle {

    public Square() {
        super(1.0, 1.0);
    }


    public Square(double side) {
        super(side, side);
    }

    public Square(double side, Color color, boolean filled) {
        super(side, side, color, filled);
    }

    public double getSide() {
        return this.getWidth();
    }

    public void setSide(double side) {
        super.setWidth(side);
        super.setLength(side);
    }

    @Override
    public void setWidth(double side) {
        this.setSide(side);
    }

    @Override
    public void setLength(double side) {
        this.setSide(side);
    }


    @Override
    public String toString() {
        return "Square[" + super.toString() + "]";
    }
}