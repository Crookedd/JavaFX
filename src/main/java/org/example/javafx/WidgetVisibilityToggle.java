package org.example.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WidgetVisibilityToggle extends Application {
    private int count = 0; // Counter variable
    private Label countLabel; // Label to display the count
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Управление видимостью виджетов");

        // Создание виджетов
        Label label = new Label("Это метка");
        TextField textField = new TextField("Это текстовое поле");
        Button button = new Button("Это кнопка");

        // Initialize the count label
        countLabel = new Label("Счет: " + count);

        // Создание чекбокс
        CheckBox labelCheckBox = new CheckBox("Показать/Скрыть метку");
        CheckBox textFieldCheckBox = new CheckBox("Показать/Скрыть текстовое поле");
        CheckBox buttonCheckBox = new CheckBox("Показать/Скрыть кнопку");

        // Обработчики для чекбокс
        labelCheckBox.setOnAction(e -> label.setVisible(labelCheckBox.isSelected()));
        textFieldCheckBox.setOnAction(e -> textField.setVisible(textFieldCheckBox.isSelected()));
        buttonCheckBox.setOnAction(e -> button.setVisible(buttonCheckBox.isSelected()));

        // Установка начального состояния видимости
        label.setVisible(labelCheckBox.isSelected());
        textField.setVisible(textFieldCheckBox.isSelected());
        button.setVisible(buttonCheckBox.isSelected());


        // Обработчик для кнопки
        button.setOnAction(e -> {
            count++; // Увеличиваем счетчик
            countLabel.setText("Счет: " + count); // Обновляем текст метки
        });


        // Создание сетки для размещения элементов
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Добавление виджетов и чекбокс в сетку
        grid.add(label, 0, 0);
        grid.add(labelCheckBox, 1, 0);
        grid.add(textField, 0, 1);
        grid.add(textFieldCheckBox, 1, 1);
        grid.add(button, 0, 2);
        grid.add(buttonCheckBox, 1, 2);
        grid.add(countLabel, 0, 3); // Adding the count label to the grid

        // Создание сцены и отображение
        Scene scene = new Scene(grid, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}