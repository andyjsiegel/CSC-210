package com.word_search_gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    private Grid grid;
    private List<String> words;
    private Text[][] textFields;
    private Text statusText;
    private TextField wordField;
    private TextField directionField;
    private TextField xField;
    private TextField yField;
    /*
     * Author: Andy Siegel
     * Class: CSC 210
     * Semester: Fall 2024
     * Project 10: Word Search GUI
     */

    // input file is a text file with the following format:

    /*
     * rows columns
     * word1
     * word2
     * ...
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Word Search Game");

        // Set up the game
        String inputFileName = "/Users/andysiegel/Library/CloudStorage/GoogleDrive-andyjsiegel1@gmail.com/My Drive/University/Semester 3/CSC 210/easy_words.txt";
        try {
            grid = setUpGame(inputFileName);
            words = new ArrayList<>(grid.wordsFound.keySet());
        } catch (Exception e) {
            System.err.println("Error setting up the game: " + e.getMessage());
            return;
        }

        // Create UI components
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for (int i = 0; i < grid.cols; i++) {
            sb.append((char) ('a' + i));
            // sb.append(" ");
        }
        Text columnsLabel = new Text(sb.toString());
        columnsLabel.setFont(Font.font("Courier New", 20));
        // Create grid of text fields
        GridPane gridPane = new GridPane();
        textFields = new Text[grid.rows][grid.cols + 3];

        for (int i = 0; i < grid.rows; i++) {
            for (int j = 0; j < grid.cols + 3; j++) {
                if (j == 0) {
                    if (i == 0) {
                        Text rowLabel = new Text("  ");
                        rowLabel.setFont(Font.font("Courier New", 20));
                        gridPane.add(rowLabel, j, i);
                    } else {
                        Text rowLabel = new Text(String.format("%02d", i));
                        rowLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
                        gridPane.add(rowLabel, j, i);
                    }
                } else if (j == 1 || j == 2) {
                    gridPane.add(new Text(" "), j, i);
                } else {
                    Text textField = new Text(String.valueOf(grid.grid[i][j - 3]));
                    if (i == 0) {
                        textField.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
                    } else {
                        textField.setFont(Font.font("Courier New", 20));
                    }
                    textFields[i][j] = textField;
                    gridPane.add(textField, j, i);
                }
            }
        }

        // Create input fields for guessing
        wordField = new TextField();
        wordField.setPromptText("Enter word");
        wordField.setOnAction(e -> xField.requestFocus());

        xField = new TextField();
        xField.setPromptText("Enter x (row)");
        xField.setOnAction(e -> yField.requestFocus());

        yField = new TextField();
        yField.setPromptText("Enter y (column)");
        yField.setOnAction(e -> directionField.requestFocus());

        directionField = new TextField();
        directionField.setPromptText("Enter direction (h/v/d)");
        directionField.setOnAction(
                e -> handleGuess(wordField.getText(), xField.getText(), yField.getText(), directionField.getText()));

        Button guessButton = new Button("Guess");
        statusText = new Text();

        // Add event handler for the guess button
        guessButton.setOnAction(
                e -> handleGuess(wordField.getText(), xField.getText(), yField.getText(), directionField.getText()));

        // Add components to the VBox
        vbox.getChildren().addAll(gridPane, wordField, xField, yField, directionField, guessButton, statusText);
        Scene scene = new Scene(vbox, 325, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleGuess(String word, String xStr, String yStr, String directionStr) {
        try {
            int x = Integer.parseInt(xStr);
            int y = yStr.toLowerCase().charAt(0) - 'a'; // Convert column letter to index
            char direction = directionStr.toLowerCase().charAt(0);

            if (grid.findWord(word.toUpperCase(), x, y, direction)) {
                grid.removeWord(word.toUpperCase(), x, y, direction);
                statusText.setText(word + " found!");
                wordField.clear();
                xField.clear();
                yField.clear();
                directionField.clear();
                boolean isGameOver = grid.isGameOver();
                updateGridDisplay(isGameOver);
                if (isGameOver) {
                    statusText.setText("All words found!");
                    ProcessBuilder pb = new ProcessBuilder("open", "raycast://extensions/raycast/raycast/confetti");
                    Process process = pb.start();
                    int exitCode = process.waitFor();
                    System.out.println("Exited with code: " + exitCode);
                }
            } else {
                statusText.setText(word + " not found.");
            }
            wordField.requestFocus();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            statusText.setText("Invalid input. Please try again.");
        }
    }

    private void updateGridDisplay(boolean gameOver) {
        for (int i = 0; i < grid.rows; i++) {
            for (int j = 0; j < grid.cols; j++) {
                textFields[i][j + 3].setText(String.valueOf(grid.grid[i][j]));
                if (gameOver) {
                    textFields[i][j + 3].setStyle("-fx-text-fill: green");
                }
            }
        }
    }

    private Grid setUpGame(String filename) throws Exception {
        // Similar to your existing setUpGame method
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String[] dimensions = reader.readLine().split(" ");
            int rows = Integer.parseInt(dimensions[1]);
            int cols = Integer.parseInt(dimensions[0]);
            Grid grid = new Grid(rows, cols, true);

            String word;
            List<String> words = new ArrayList<>();
            while ((word = reader.readLine()) != null) {
                words.add(word.toUpperCase());
            }

            grid.fillGridWithWords(words);
            return grid;
        }
    }
}
