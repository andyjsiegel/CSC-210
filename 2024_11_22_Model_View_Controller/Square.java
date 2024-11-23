public class Square extends Shape {
    private int side;
    public Square(int side) {
        this.side = side;
    }
    public double getArea() {
        return side * side;
    }
}
