import java.util.ArrayList;

public class Controller {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    public void parseLine(String line) {
        // File format: shape,dimension info, x, y, color
        /*
        triangle,side:10,100,150,BLUE
        square,side:15,20,15,YELLOW
        circle,radius:12,50,60,GREEN */
        String[] components = line.split(",");
        String shape = components[0];
        String[] dimensions = components[1].split(":");
        if (shape.equals("triangle")) {
            shapes.add(new Triangle(Integer.parseInt(dimensions[1])));
        } else if (shape.equals("square")) {
            shapes.add(new Square(Integer.parseInt(dimensions[1])));
        } else if (shape.equals("circle")) {
            shapes.add(new Circle(Integer.parseInt(dimensions[1])));
        }
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Shape shape : shapes) {
            builder.append(shape.toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
