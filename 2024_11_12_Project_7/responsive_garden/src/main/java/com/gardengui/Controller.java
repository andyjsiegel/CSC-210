package com.gardengui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Controller {

    private Garden garden;
    @FXML
    private TextArea gardenView;
    @FXML
    private TextArea commandLog;
    @FXML
    private TextField commandInput;
    @FXML
    private TextField rowInput; // TextField for rows
    @FXML
    private TextField colInput; // TextField for columns
    @FXML
    private Button setSizeButton; // Button to set rows and columns

    private int rows = 5;
    private int cols = 5;

    public VBox createUI() {
        VBox vbox = new VBox();

        gardenView = new TextArea();
        gardenView.setEditable(false);
        gardenView.setPrefHeight(600); // Set a preferred height for the garden view
        garden = new Garden(rows, cols);
        gardenView.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 16;");
        gardenView.setText(garden.toString());

        commandLog = new TextArea();
        commandLog.setEditable(false);
        commandLog.setPrefHeight(100); // Set a preferred height for the command log
        commandLog.setMaxHeight(100); // Ensure it does not grow too tall

        commandInput = new TextField();
        commandInput.setPromptText("Enter a command:");
        Button simulateButton = new Button("Simulate");

        // Create fields for row and column input
        rowInput = new TextField();
        rowInput.setPromptText("Enter number of rows");
        colInput = new TextField();
        colInput.setPromptText("Enter number of columns");
        setSizeButton = new Button("Set Size");

        // Set action for button and text field
        simulateButton.setOnAction(event -> executeCommand());
        commandInput.setOnAction(event -> executeCommand());
        setSizeButton.setOnAction(event -> setGardenSize());

        vbox.getChildren().addAll(gardenView, commandLog, commandInput, simulateButton, rowInput, colInput, setSizeButton);
        return vbox;
    }

    private void executeCommand() {
        String command = commandInput.getText();
        try {
            garden.parseCommand(command);
            String hexCode = "#ff0000";
            if (command.toUpperCase().contains("GROW") || command.toUpperCase().contains("PLANT")) {
                hexCode = "#008000"; // nice green color
            }
            commandLog.appendText("Executing: " + command + "\n");
            gardenView.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 16; -fx-text-fill: " + hexCode);
            PauseTransition pause = new PauseTransition(Duration.millis(350));
            pause.setOnFinished(event -> {
                // Change the style back to black after the pause
                gardenView.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 16; -fx-text-fill: #000000");
            });
            pause.play();
        } catch (Exception e) {
            commandLog.appendText("\"" + command + "\" threw an exception: " + e.toString() + "\n");
        }
        updateGardenView();
        commandInput.clear();
    }

    private void setGardenSize() {
        try {
            int newRows = Integer.parseInt(rowInput.getText());
            int newCols = Integer.parseInt(colInput.getText());

            if (newRows > 0 && newCols > 0) {
                this.rows = newRows;
                this.cols = newCols;
                garden = new Garden(rows, cols); // Recreate the garden with new dimensions
                commandLog.appendText("Garden size set to " + rows + " rows and " + cols + " columns.\n");
                updateGardenView();
            } else {
                commandLog.appendText("Please enter positive integers for rows and columns.\n");
            }
        } catch (NumberFormatException e) {
            commandLog.appendText("Invalid input. Please enter valid integers for rows and columns.\n");
        }
        rowInput.clear();
        colInput.clear();
    }

    private void updateGardenView() {
        gardenView.setText(garden.toString());
    }
}
