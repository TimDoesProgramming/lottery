
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {
    private Label resultLabel;
    private Label statusLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Win Statistics Calculator");

        // Create UI elements
        Label titleLabel = new Label("Win Statistics Calculator");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        statusLabel = new Label("Ready");
        statusLabel.setStyle("-fx-font-size: 12; -fx-text-fill: gray;");

        resultLabel = new Label("Click 'Calculate' to start");
        resultLabel.setStyle("-fx-font-size: 14;");

        Button calculateButton = new Button("Calculate");
        calculateButton.setStyle("-fx-font-size: 14; -fx-padding: 10;");
        calculateButton.setOnAction(e -> calculateStats());

        Button resetButton = new Button("Reset");
        resetButton.setStyle("-fx-font-size: 14; -fx-padding: 10;");
        resetButton.setOnAction(e -> resetDisplay());

        // Layout
        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(titleLabel, statusLabel, resultLabel, calculateButton, resetButton);

        Scene scene = new Scene(vbox, 450, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateStats() {
        statusLabel.setText("Calculating... (this may take a moment)");
        resultLabel.setText("");

        Thread thread = new Thread(() -> {
            try {
                Random rand = new Random();
                int win = 1;
                int lastWinIndex = 0;
                int currentWinIndex = 0;
                int totalWins = 0;
                int totalOfWinIndex = 0;

                for(int i = 1; i < 1000000000; i++) {
                    //generates a random number between 1 and 625, origin is inclusive, bound is exclusive
                    int randomNum = rand.nextInt(1, 626);
                    if(randomNum == win) {
                        currentWinIndex = i;
                        totalWins++;
                        totalOfWinIndex += currentWinIndex - lastWinIndex;
                        lastWinIndex = currentWinIndex;
                    }
                }

                double averageWinIndex = (double) totalOfWinIndex / totalWins;
                String result = String.format("Average Win Index: %.2f\nTotal Wins: %d", averageWinIndex, totalWins);

                // Update UI on JavaFX thread
                javafx.application.Platform.runLater(() -> {
                    resultLabel.setText(result);
                    statusLabel.setText("Calculation complete!");
                });
            } catch (Exception e) {
                javafx.application.Platform.runLater(() -> {
                    resultLabel.setText("Error: " + e.getMessage());
                    statusLabel.setText("Error occurred");
                });
            }
        });

        thread.setDaemon(true);
        thread.start();
    }

    private void resetDisplay() {
        resultLabel.setText("Click 'Calculate' to start");
        statusLabel.setText("Ready");
    }

    public static void main(String[] args) {
        launch(args);
    }
}