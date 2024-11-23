public class Shape {
    protected int x;
    protected int y;
    protected Color color;
    public enum Color {
        RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE
    }
    public double getArea() {
        return 0;
    }
    public String toString() {
        return getArea() + "";
    }
}
