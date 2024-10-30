package com.gardengui;

/*
 * CSC 210 - Fall 2024 
 * Project 6 - Garden GUI Output
 * Andy Siegel
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @SuppressWarnings("exports")
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        VBox root = loader.load();
        primaryStage.setTitle("Andy's Garden Simulator");
        primaryStage.setScene(new Scene(root, 270, 765));
        primaryStage.show();
    }   

    public static void main(String[] args) {
        launch(args);
    }
}
