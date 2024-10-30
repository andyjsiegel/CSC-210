package com.gardengui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Controller {

    private Garden garden;
    private Timeline timeline;
    @FXML
    private TextArea gardenView;
    @FXML
    private TextArea commandView;
    private String filePath = "/Users/andysiegel/Library/CloudStorage/GoogleDrive-andyjsiegel1@gmail.com/My Drive/University of Arizona/Semester 3/CSC 210/2024_10_24_Project_6/TestCases/myGarden.in";
    @FXML
    public void initialize() throws FileNotFoundException {
        garden = RunGarden.createGardenFromFile(filePath);
        gardenView.setText(garden.toString());
        startUpdatingGardenView();
    }

    private void startUpdatingGardenView() throws FileNotFoundException {
        Scanner fileReader = new Scanner(new File(filePath));
        fileReader.nextLine(); fileReader.nextLine(); // skip rows and columns
        double delay = Double.valueOf(fileReader.nextLine().split(" ")[1]);
        fileReader.nextLine();
        timeline = new Timeline(new KeyFrame(Duration.seconds(delay), event -> updateGardenView(fileReader)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    private void updateGardenView(Scanner reader) {
        if(reader.hasNextLine()) {
            String command = reader.nextLine();
            commandView.appendText(command + "\n");
            garden.parseCommand(command);
            gardenView.setText(garden.toString());
        } else {
            reader.close();
            timeline.stop();
            gardenView.appendText("\nGarden simulation complete.");
        }
    }
}
