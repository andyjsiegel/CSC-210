package com.foodapp;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private int orderCounter = 1;
    private FoodOrder currentOrder;
    private ArrayList<FoodOrder> orders = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Andy Siegel Fall 2024 Food Ordering App");

        BorderPane myPanel = new BorderPane();

        // where all orders are shown
        TextArea myTextArea = new TextArea();
        myTextArea.setPrefHeight(200);
        myTextArea.setEditable(false);
        myPanel.setBottom(myTextArea);

        GridPane myGrid = new GridPane();
        myGrid.setPadding(new Insets(10, 10, 10, 10));
        myGrid.setVgap(10);  // Increased vertical gap for spacing
        myGrid.setHgap(10);  // Increased horizontal gap for spacing
        myPanel.setCenter(myGrid);

        currentOrder = createNewOrder();
        
        TextField orderNumberField = new TextField(currentOrder.getOrderNumber());
        orderNumberField.setEditable(false);
        GridPane.setConstraints(orderNumberField, 0, 0);
        myGrid.getChildren().add(orderNumberField);

        Label statusLabel = new Label("Order Status: " + currentOrder.getStatus());
        GridPane.setConstraints(statusLabel, 1, 0);
        myGrid.getChildren().add(statusLabel);

        Button placeOrderButton = new Button("Place Order");
        GridPane.setConstraints(placeOrderButton, 0, 1);
        myGrid.getChildren().add(placeOrderButton);

        placeOrderButton.setOnAction((actionEvent) -> {
            currentOrder.placeOrder();
            orders.add(currentOrder);  // Add the completed order to the list
            statusLabel.setText("Order Status: " + currentOrder.getStatus());
            myTextArea.clear();
            myTextArea.appendText(currentOrder.toString());

            // Start a new order automatically once an order is placed
            currentOrder = createNewOrder();
            orderNumberField.setText(currentOrder.getOrderNumber());
            statusLabel.setText("Order Status: " + currentOrder.getStatus());
        });

        Button viewAllOrdersButton = new Button("View All Orders");
        GridPane.setConstraints(viewAllOrdersButton, 1, 1);
        myGrid.getChildren().add(viewAllOrdersButton);

        viewAllOrdersButton.setOnAction((actionEvent) -> {
            myTextArea.clear();
            myTextArea.appendText("All Orders:\n");
            for (FoodOrder order : orders) {
                myTextArea.appendText(order.toString() + "\n\n");
            }
            myTextArea.appendText(currentOrder.toString());
        });

        // Add size and flavor options for pizza
        final ToggleGroup groupSize = addSizeOptions(myGrid);
        final ToggleGroup groupFlavor = addFlavorOptions(myGrid);

        // Add pizza button
        Button addPizza = new Button("Add Pizza");
        GridPane.setConstraints(addPizza, 2, 3);
        myGrid.getChildren().add(addPizza);

        // Action for add pizza button
        addPizza.setOnAction((actionEvent) -> {
            Pizza.Flavor flavor = (Pizza.Flavor) groupFlavor.getSelectedToggle().getUserData();
            Pizza.Size size = (Pizza.Size) groupSize.getSelectedToggle().getUserData();
            Food myFood = new Pizza(flavor, size);
            currentOrder.addFood(myFood);
            myTextArea.appendText(myFood.toString());
        });

        // Add burger button
        Button addBurger = new Button("Add Burger");
        GridPane.setConstraints(addBurger, 3, 3);
        myGrid.getChildren().add(addBurger);

        // Action for add burger button
        addBurger.setOnAction((actionEvent) -> {
            Food myFood = new Burger();
            currentOrder.addFood(myFood);
            myTextArea.appendText(myFood.toString());
        });

        // Remove food item button
        Button removePizza = new Button("Remove Last Food");
        GridPane.setConstraints(removePizza, 4, 3);
        myGrid.getChildren().add(removePizza);

        removePizza.setOnAction((actionEvent) -> {
            if (!currentOrder.items.isEmpty()) {
                Food lastFood = currentOrder.items.get(currentOrder.items.size() - 1);
                currentOrder.removeFood(lastFood);
                myTextArea.clear();
                myTextArea.appendText(currentOrder.toString());
            } else {
                myTextArea.appendText("\nNo items to remove.\n");
            }
        });

        HBox statusButtonsBox = new HBox(10);
        statusButtonsBox.setPadding(new Insets(10));
        addStatusUpdateButtons(statusButtonsBox, statusLabel, myTextArea);
        GridPane.setConstraints(statusButtonsBox, 0, 4, 5, 1);
        myGrid.getChildren().add(statusButtonsBox);

        // Set scene and display
        scene = new Scene(myPanel, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    private FoodOrder createNewOrder() {
        // increment order counter and then make a new food order with it
        String newOrderNumber = String.format("%04d", orderCounter++);
        return new FoodOrder(newOrderNumber);
    }

    private void addStatusUpdateButtons(HBox statusButtonsBox, Label statusLabel, TextArea myTextArea) {
        Button readyButton = new Button("Set to Ready");
        statusButtonsBox.getChildren().add(readyButton);
        readyButton.setOnAction(e -> {
            if (currentOrder.setStatus(FoodOrder.OrderStatus.READY)) {
                // if order status can be set to this, then set it. 
                statusLabel.setText("Order Status: " + currentOrder.getStatus());
            } else {
                // if it cant, append new text as an error msg
                myTextArea.appendText("\nCannot change status to READY from current status.\n");
            }
        });

        Button inRouteButton = new Button("Set to In Route");
        statusButtonsBox.getChildren().add(inRouteButton);
        inRouteButton.setOnAction(e -> {
            if (currentOrder.setStatus(FoodOrder.OrderStatus.INROUTE)) {
                statusLabel.setText("Order Status: " + currentOrder.getStatus());
            } else {
                myTextArea.appendText("\nCannot change status to IN ROUTE from current status.\n");
            }
        });

        Button deliveredButton = new Button("Set to Delivered");
        statusButtonsBox.getChildren().add(deliveredButton);
        deliveredButton.setOnAction(e -> {
            if (currentOrder.setStatus(FoodOrder.OrderStatus.DELIVERED)) {
                statusLabel.setText("Order Status: " + currentOrder.getStatus());
            } else {
                myTextArea.appendText("\nCannot change status to DELIVERED from current status.\n");
            }
        });
    }

    private ToggleGroup addSizeOptions(GridPane myGrid) {
        final ToggleGroup groupSize = new ToggleGroup();

        RadioButton small = new RadioButton("Small");
        small.setSelected(true);
        small.setToggleGroup(groupSize);
        small.setUserData(Pizza.Size.PERSONAL);
        GridPane.setConstraints(small, 2, 0);
        myGrid.getChildren().add(small);

        RadioButton medium = new RadioButton("Medium");
        medium.setToggleGroup(groupSize);
        medium.setUserData(Pizza.Size.MEDIUM);
        GridPane.setConstraints(medium, 2, 1);
        myGrid.getChildren().add(medium);

        RadioButton large = new RadioButton("Large");
        large.setToggleGroup(groupSize);
        large.setUserData(Pizza.Size.LARGE);
        GridPane.setConstraints(large, 2, 2);
        myGrid.getChildren().add(large);

        return groupSize;
    }

    private ToggleGroup addFlavorOptions(GridPane myGrid) {
        final ToggleGroup groupFlavor = new ToggleGroup();

        RadioButton cheese = new RadioButton("Cheese");
        cheese.setSelected(true);
        cheese.setToggleGroup(groupFlavor);
        cheese.setUserData(Pizza.Flavor.CHEESE);
        GridPane.setConstraints(cheese, 3, 0);
        myGrid.getChildren().add(cheese);

        RadioButton pepperoni = new RadioButton("Pepperoni");
        pepperoni.setToggleGroup(groupFlavor);
        pepperoni.setUserData(Pizza.Flavor.PEPPERONI);
        GridPane.setConstraints(pepperoni, 3, 1);
        myGrid.getChildren().add(pepperoni);

        return groupFlavor;
    }

    public static void main(String[] args) {
        launch();
    }
}
