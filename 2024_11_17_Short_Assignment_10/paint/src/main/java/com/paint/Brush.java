package com.paint;

public class Brush {
    private int size;
    private String color; //hex code with '#'
    private String nonEraserColor;
    public enum Shape { CIRCLE, SQUARE, TRIANGLE };
    private Shape shape = Shape.CIRCLE;

    public Brush(int size, String color) {
        this.size = size;
        this.color = color;
        this.nonEraserColor = color;
    }

    public int getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.nonEraserColor = color;
        this.color = color;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Shape getShape() {
        return this.shape;
    }

    public void turnOnEraser() {
        this.color = "#ffffff";
    }
    public void turnOffEraser() {
        this.color = this.nonEraserColor;
    }
}
