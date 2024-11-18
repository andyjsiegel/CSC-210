package com.paint;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

/**
 * Andy Siegel Fall 2024 MS Paint App
 */
public class App extends Application {

    private static Scene scene;
    private static Brush paintbrush = new Brush(5, "#000000");

    public static void main(String[] args) {
        launch();
    }

    public void drawDot(int x, int y, Pane drawingArea) {
        Circle circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setFill(Color.web(paintbrush.getColor(), 1.0));
        circle.setRadius(paintbrush.getSize());
        drawingArea.getChildren().add(circle);
    }

    public void drawSquare(int x, int y, Pane drawingArea) {
        Rectangle rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setFill(Color.web(paintbrush.getColor(), 1.0));
        rectangle.setWidth(paintbrush.getSize());
        rectangle.setHeight(paintbrush.getSize());
        drawingArea.getChildren().add(rectangle);
    }

    public void drawTriangle(int x, int y, Pane drawingArea) {
        SVGPath svgPath = new SVGPath();
        // Define the points for a centered triangle
        // Centering with respect to (x, y)
        int size = paintbrush.getSize();
        svgPath.setContent("M " + (x) + " " + (y - size) + // Top vertex
                           " L " + (x - size) + " " + (y + size) + // Bottom left
                           " L " + (x + size) + " " + (y + size) + // Bottom right
                           " Z"); // 'Z' closes the path
        svgPath.setFill(Color.web(paintbrush.getColor(), 1.0));
        drawingArea.getChildren().add(svgPath);
    }
    
    public void draw(int x, int y, Pane drawingArea) {
        String shape = String.valueOf(paintbrush.getShape());
        switch (shape) {
            case "CIRCLE":
                drawDot(x, y, drawingArea);
                break;
            case "SQUARE":
                drawSquare(x, y, drawingArea);
                break;
            case "TRIANGLE":
                drawTriangle(x, y, drawingArea);
                break;
        }
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Andy Siegel Fall 2024 MS Paint Clone App");

        // Set up the drawing area
        Pane drawingArea = new Pane();
        drawingArea.setPrefSize(640, 480);
        drawingArea.setStyle("-fx-background-color: white;");

        // Set up buttons
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> drawingArea.getChildren().clear());

        Button blackButton = new Button("Black");
        blackButton.setOnAction(e -> {
            paintbrush.setColor("#000000");
            System.out.println("Black button clicked!");
        });

        Button redButton = new Button("Red");
        redButton.setOnAction(e -> {
            paintbrush.setColor("#ff0000");
            System.out.println("Red button clicked!");
        });

        Button orangeButton = new Button("Orange");
        orangeButton.setOnAction(e -> {
            paintbrush.setColor("#ffa500");
            System.out.println("Orange button clicked!");
        });

        Button yellowButton = new Button("Yellow");
        yellowButton.setOnAction(e -> {
            paintbrush.setColor("#ffff00");
            System.out.println("Yellow button clicked!");
        });

        Button greenButton = new Button("Green");
        greenButton.setOnAction(e -> {
            paintbrush.setColor("#00ff00");
            System.out.println("Green button clicked!");
        });

        Button blueButton = new Button("Blue");
        blueButton.setOnAction(e -> {
            paintbrush.setColor("#0000ff");
            System.out.println("Blue button clicked!");
        });

        Button purpleButton = new Button("Purple");
        purpleButton.setOnAction(e -> {
            paintbrush.setColor("#9138FF");
            System.out.println("Purple button clicked!");
        });
        
        Label sizeLabel = new Label("Size: " + paintbrush.getSize() + "px");
        sizeLabel.setAlignment(Pos.CENTER);

        Button increaseSizeButton = new Button("+ Size");
        increaseSizeButton.setOnMouseClicked(event -> {
            if(event.isControlDown()) {
                paintbrush.setSize(paintbrush.getSize() + 5);
            } else {
                paintbrush.setSize(paintbrush.getSize() + 1);
            }
            sizeLabel.setText("Size: " + paintbrush.getSize() + "px");
            System.out.println("Increase size button clicked!");
        });

        Button decreaseSizeButton = new Button("- Size");
        decreaseSizeButton.setOnMouseClicked(event -> {
            if(event.isControlDown()) {
                paintbrush.setSize(paintbrush.getSize() - 5);
            } else {
                paintbrush.setSize(paintbrush.getSize() - 1);
            }
            sizeLabel.setText("Size: " + paintbrush.getSize() + "px");
            System.out.println("Decrease size button clicked!");
        });

        Label spacerLabel = new Label("     ");
        spacerLabel.setAlignment(Pos.CENTER);

        Label spacerLabel2 = new Label("     ");
        spacerLabel.setAlignment(Pos.CENTER);

        Button squareButton = new Button("Square");
        squareButton.setOnAction(e -> {
            paintbrush.setShape(Brush.Shape.SQUARE);
            System.out.println("Square button clicked!");
        });
        
        Button triangleButton = new Button("Triangle");
        triangleButton.setOnAction(e -> {
            paintbrush.setShape(Brush.Shape.TRIANGLE);
            System.out.println("Triangle button clicked!");
        });
        
        Button circleButton = new Button("Circle");
        circleButton.setOnAction(e -> {
            paintbrush.setShape(Brush.Shape.CIRCLE);
            System.out.println("Circle button clicked!");
        });

        // Create an HBox for horizontal layout of buttons
        HBox buttonBar = new HBox(10); // 10px spacing between buttons
        buttonBar.setAlignment(Pos.CENTER);
        buttonBar.getChildren().addAll(clearButton, blackButton, redButton, orangeButton, yellowButton, greenButton, blueButton, purpleButton, spacerLabel, increaseSizeButton, sizeLabel, decreaseSizeButton, spacerLabel2, circleButton, squareButton, triangleButton);

        // Set up the main layout
        BorderPane root = new BorderPane();
        root.setCenter(drawingArea);
        root.setTop(buttonBar);

        // Set up the scene
        scene = new Scene(root, 1000, 480);

        drawingArea.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();
            // Filter by left click only
            if (mouseEvent.isPrimaryButtonDown()) {
                draw(x, y, drawingArea);
                // drawDot(x, y, drawingArea);
            }
        });

        drawingArea.addEventFilter(MouseEvent.MOUSE_DRAGGED, mouseEvent -> {
            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();
            // Filter by left click only
            if (mouseEvent.isPrimaryButtonDown()) {
                draw(x, y, drawingArea);
                // drawDot(x, y, drawingArea);
            } else { // If right click, then erase
                paintbrush.turnOnEraser();
                draw(x, y, drawingArea);
                // drawDot(x, y, drawingArea);
            }
        });

        drawingArea.addEventFilter(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            paintbrush.turnOffEraser();
        });

        stage.setScene(scene);
        stage.show();
    }
}
