package org.example.javafx;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WordSwitcher extends Application {
    private boolean isForward = true; // Флаг для направления стрелки
    private TextField inputField;
    private TextField outputField;
    private Button switchButton;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Перекидыватель слов");

        inputField = new TextField();
        outputField = new TextField();
        outputField.setEditable(false); // Выходное поле только для чтения

        switchButton = new Button("→"); // Начальная стрелка
        switchButton.setOnAction(e -> switchWords());

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        grid.add(inputField, 0, 0);
        grid.add(switchButton, 1, 0);
        grid.add(outputField, 2, 0);

        Scene scene = new Scene(grid, 400, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void switchWords() {
        if (isForward) {
            outputField.setText(inputField.getText());
            switchButton.setText("←"); // Меняем стрелку на обратную
        } else {
            inputField.setText(outputField.getText());
            switchButton.setText("→"); // Меняем стрелку на изначальную
        }
        isForward = !isForward; // Меняем направление
    }

    public static void main(String[] args) {
        launch(args);
    }
}