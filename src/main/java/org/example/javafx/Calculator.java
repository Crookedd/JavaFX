package org.example.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Stack;

public class Calculator extends Application {
    private TextField inputField;
    private final StringBuilder currentInput = new StringBuilder();
    private static final int BUTTON_SIZE = 40;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Калькулятор");

        inputField = new TextField();
        inputField.setEditable(false);
        inputField.setPrefWidth(300);

        Button[] numberButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = createButton(String.valueOf(i));
            int finalI = i;
            numberButtons[i].setOnAction(e -> appendToInput(String.valueOf(finalI)));
        }

        Button addButton = createButton("+");
        Button subtractButton = createButton("-");
        Button multiplyButton = createButton("*");
        Button divideButton = createButton("/");
        Button equalsButton = createButton("=");
        Button clearButton = createButton("C");

        addButton.setOnAction(e -> appendToInput(" + "));
        subtractButton.setOnAction(e -> appendToInput(" - "));
        multiplyButton.setOnAction(e -> appendToInput(" * "));
        divideButton.setOnAction(e -> appendToInput(" / "));
        equalsButton.setOnAction(e -> calculateResult());
        clearButton.setOnAction(e -> clearInput());

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        grid.add(inputField, 0, 0, 4, 1);
        int row = 1;
        for (int i = 1; i <= 9; i++) {
            grid.add(numberButtons[i], (i - 1) % 3, row);
            if (i % 3 == 0) row++;
        }
        grid.add(numberButtons[0], 1, row);
        grid.add(addButton, 3, 1);
        grid.add(subtractButton, 3, 2);
        grid.add(multiplyButton, 3, 3);
        grid.add(divideButton, 3, 4);
        grid.add(equalsButton, 2, row);
        grid.add(clearButton, 0, row);

        Scene scene = new Scene(grid, 200, 200);
        stage.setScene(scene);
        stage.show();
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(BUTTON_SIZE, BUTTON_SIZE); // Установка размера кнопки
        return button;
    }

    private void appendToInput(String value) {
        currentInput.append(value);
        inputField.setText(currentInput.toString());
    }

    private void calculateResult() {
        try {
            String result = evaluateExpression(currentInput.toString());
            inputField.setText(result);
            currentInput.setLength(0); // Сбросить текущее выражение
            currentInput.append(result);
        } catch (ArithmeticException e) {
            showError("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            showError("Ошибка: Неверный ввод");
        }
    }

    private String evaluateExpression(String expression) {
        String[] tokens = expression.split(" ");
        Stack<Double> values = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (String token : tokens) {
            if (token.matches("\\d+")) { // If the token is a number
                values.push(Double.parseDouble(token));
            } else if (token.equals("+") || token.equals("-")) {
                while (!operators.isEmpty() && (operators.peek().equals("*") || operators.peek().equals("/"))) {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(token);
            } else if (token.equals("*") || token.equals("/")) {
                while (!operators.isEmpty() && (operators.peek().equals("*") || operators.peek().equals("/"))) {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(token);
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
        }

        return String.valueOf(values.pop());
    }

    private Double applyOperation(String operator, double b, double a) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) {
                    throw new ArithmeticException("Деление на ноль");
                }
                yield a / b;
            }
            default -> (double) 0;
        };

    }

    private void clearInput() {
        currentInput.setLength(0);
        inputField.clear();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Ошибка");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
