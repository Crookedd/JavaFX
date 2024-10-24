package org.example.javafx;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Flag extends Application {

    private String color1 = "Красный";
    private String color2 = "Зеленый";
    private String color3 = "Белый";

    @Override
    public void start(Stage stage) {
        stage.setTitle("Текстовый флаг");

        // Создание радиокнопок для выбора цвета
        ToggleGroup group1 = new ToggleGroup();
        ToggleGroup group2 = new ToggleGroup();
        ToggleGroup group3 = new ToggleGroup();

        RadioButton red1 = createRadioButton("Красный", group1);
        RadioButton green1 = createRadioButton("Зеленый", group1);
        RadioButton blue1 = createRadioButton("Синий", group1);
        RadioButton while1 = createRadioButton("Белый", group1);

        RadioButton red2 = createRadioButton("Красный", group2);
        RadioButton green2 = createRadioButton("Зеленый", group2);
        RadioButton blue2 = createRadioButton("Синий", group2);
        RadioButton while2  = createRadioButton("Белый", group2);

        RadioButton red3 = createRadioButton("Красный", group3);
        RadioButton green3 = createRadioButton("Зеленый", group3);
        RadioButton blue3 = createRadioButton("Синий", group3);
        RadioButton while3 = createRadioButton("Белый", group3);

        Button drawButton = new Button("Нарисовать");
        Label resultLabel = new Label();

        drawButton.setOnAction(e -> {
            color1 = getSelectedColor(group1);
            color2 = getSelectedColor(group2);
            color3 = getSelectedColor(group3);
            resultLabel.setText("Цвета флага: " + color1 + ", " + color2 + ", " + color3);
        });

        // Настройка сетки
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(new Label("Полоса 1:"), 0, 0);
        grid.add(red1, 0, 1);
        grid.add(green1, 1, 1);
        grid.add(blue1, 2, 1);
        grid.add(while1, 3, 1);


        grid.add(new Label("Полоса 2:"), 0, 2);
        grid.add(red2, 0, 3);
        grid.add(green2, 1, 3);
        grid.add(blue2, 2, 3);
        grid.add(while2, 3, 3);

        grid.add(new Label("Полоса 3:"), 0, 4);
        grid.add(red3, 0, 5);
        grid.add(green3, 1, 5);
        grid.add(blue3, 2, 5);
        grid.add(while3, 3, 5);

        grid.add(drawButton, 0, 6, 3, 1);
        grid.add(resultLabel, 0, 7, 3, 1);

        Scene scene = new Scene(grid, 350, 250);
        stage.setScene(scene);
        stage.setResizable(false); // Запрет изменения размера окна
        stage.show();
    }

    private RadioButton createRadioButton(String text, ToggleGroup group) {
        RadioButton radioButton = new RadioButton(text);
        radioButton.setToggleGroup(group);
        return radioButton;
    }

    private String getSelectedColor(ToggleGroup group) {
        RadioButton selected = (RadioButton) group.getSelectedToggle();
        return selected != null ? selected.getText() : "Не выбран";
    }

    public static void main(String[] args) {
        launch(args);
    }
}