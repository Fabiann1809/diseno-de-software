package Shape;

public class Shape {
    private Color color;
    private boolean filled;


    public Shape() {
        this.color = Color.ROJO;
        this.filled = true;
    }


    public Shape(Color color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public Color getColor() {

        return color;
    }

    public void setColor(Color color) {

        this.color = color;
    }


    public boolean isFilled() {

        return filled;
    }

    public void setFilled(boolean filled) {

        this.filled = filled;
    }

    @Override
    public String toString() {
        return "Shape[color=" + this.color + ",filled=" + this.filled + "]";
    }
}