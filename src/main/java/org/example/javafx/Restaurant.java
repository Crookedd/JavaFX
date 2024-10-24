package org.example.javafx;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends Application {

    private static class Dish {
        String name;
        double price;

        Dish(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    private final List<Dish> menu = List.of(
            new Dish("Паста", 250),
            new Dish("Пицца", 300),
            new Dish("Салат", 150)
    );

    private final List<CheckBox> dishCheckBoxes = new ArrayList<>();
    private final List<TextField> quantityFields = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Заказ в ресторане");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Создание интерфейса для выбора блюд
        for (int i = 0; i < menu.size(); i++) {
            Dish dish = menu.get(i);
            CheckBox checkBox = new CheckBox(dish.name + " - " + dish.price + "₽");
            TextField quantityField = new TextField("1");
            quantityField.setPrefWidth(50);
            quantityField.setDisable(true);

            checkBox.setOnAction(e -> quantityField.setDisable(!checkBox.isSelected()));
            dishCheckBoxes.add(checkBox);
            quantityFields.add(quantityField);

            grid.add(checkBox, 0, i);
            grid.add(quantityField, 1, i);
        }

        Button orderButton = new Button("Оформить заказ");
        orderButton.setOnAction(e -> showReceipt());
        grid.add(orderButton, 0, menu.size());

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showReceipt() {
        StringBuilder receipt = new StringBuilder("Чек:\n");
        double totalCost = 0;

        for (int i = 0; i < menu.size(); i++) {
            if (dishCheckBoxes.get(i).isSelected()) {
                int quantity = Integer.parseInt(quantityFields.get(i).getText());
                double cost = quantity * menu.get(i).price;
                totalCost += cost;
                receipt.append(menu.get(i).name)
                        .append(" - Количество: ")
                        .append(quantity)
                        .append(", Итоговая стоимость: ")
                        .append(cost)
                        .append("₽\n");
            }
        }

        receipt.append("Суммарная стоимость заказа: ").append(totalCost).append("₽");

        showReceiptDialog(receipt.toString());
    }

    private void showReceiptDialog(String receipt) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Чек");

        TextArea receiptArea = new TextArea(receipt);
        receiptArea.setEditable(false);
        receiptArea.setWrapText(true);

        Button closeButton = new Button("Закрыть");
        closeButton.setOnAction(e -> dialog.close());

        VBox dialogVbox = new VBox(10, receiptArea, closeButton);
        dialogVbox.setPadding(new Insets(10));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}