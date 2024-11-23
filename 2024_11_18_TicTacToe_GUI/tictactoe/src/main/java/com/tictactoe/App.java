package com.tictactoe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static String currentPlayer = "X";
    private static int numTurnsTaken = 0;
    private static ArrayList<ArrayList<Character>> board;

    @Override
    public void start(Stage stage) throws IOException {
        GridPane ticTacToeGrid = new GridPane();
        ticTacToeGrid.setPrefSize(400, 400);
        ticTacToeGrid.setAlignment(Pos.CENTER);
        board = new ArrayList<ArrayList<Character>>();
        for(int i = 0; i < 3; i++) {
            ArrayList<Character> row = new ArrayList<Character>();
            for(int j = 0; j < 3; j++) {
                row.add('.'); // Fill with '.'
                Button button = new Button();
                button.setPrefSize(100, 100);
                button.setStyle("-fx-font-size: 45px;");
                button.setOnAction(e -> {
                    Button tempButton = (Button) e.getSource();
                    if(tempButton.getText().isEmpty()) {
                        tempButton.setText(currentPlayer);
                        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                        numTurnsTaken++;
                        if(numTurnsTaken == 9) {
                            System.out.println("It's a tie!");
                        }
                    }
                });
                ticTacToeGrid.add(button, j, i);
            }
            board.add(row);
        }
        System.out.println(board.toString());
        scene = new Scene(ticTacToeGrid);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}