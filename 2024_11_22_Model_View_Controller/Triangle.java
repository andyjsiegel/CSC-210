public class Triangle extends Shape {
    private int side;
    public Triangle(int side) {
        this.side = side;
    }
    public double getArea() {
        return (side * side) * Math.sqrt(3) / 4;
    }
}
