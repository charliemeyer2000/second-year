package edu.virginia.cs.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatorController {
    @FXML
    private TextField firstTextField;

    @FXML
    private ChoiceBox<String> operationChoiceBox;

    @FXML
    private TextField secondTextField;

    @FXML
    private Label resultLabel;

    @FXML
    private Label errorLabel;

    public void initialize() {
        errorLabel.setStyle("-fx-text-fill: red");
    }

    public void handleCalculate() {
        try {
            errorLabel.setText(""); // clear existing error message

            var firstNumber = Double.parseDouble(firstTextField.getText());
            var secondNumber = Double.parseDouble(secondTextField.getText());
            var operation = operationChoiceBox.getValue();
            if (secondNumber == 0.0 && operation.equals("/")) {
                errorLabel.setText("Cannot divide by zero");
                resultLabel.setText("= ERROR");
                return;
            }
            var result = switch(operation) {
                case "+" -> firstNumber + secondNumber;
                case "-" -> firstNumber - secondNumber;
                case "*" -> firstNumber * secondNumber;
                case "/" -> firstNumber / secondNumber;
                default -> throw new UnsupportedOperationException();
            };

            resultLabel.setText("= " + result);

        } catch (NumberFormatException e) {
            errorLabel.setText("At least one of your inputs is not a well-formatted number. Try again!");
            resultLabel.setText("= ERROR");
        }
    }
}
