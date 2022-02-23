package com.zkurdya.javafxcalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.FileWriter;
import java.io.IOException;

public class Controller {
    @FXML
    private Label result, operations;
    boolean operationSelected = false;

    @FXML
    protected void getValue(ActionEvent event) {
        String temp = ((Button)event.getSource()).getText();
        switch (temp) {
            case "C" -> {
                operations.setText("");
                result.setText("0");
            }
            case "+" -> {
                if (!operationSelected){
                    operations.setText(operations.getText() + "+");
                    operationSelected = true;
                }
            }
            case "-" -> {
                if (!operationSelected){
                    operations.setText(operations.getText() + "-");
                    operationSelected = true;
                }
            }
            case "×" -> {
                if (!operationSelected){
                    operations.setText(operations.getText() + "*");
                    operationSelected = true;
                }
            }
            case "÷" -> {
                if (!operationSelected){
                    operations.setText(operations.getText() + "/");
                    operationSelected = true;
                }
            }
            case "(" -> operations.setText(operations.getText() + "(");
            case ")" -> operations.setText(operations.getText() + ")");
            default -> {
                operations.setText(operations.getText() + Integer.parseInt(temp));
                operationSelected = false;
            }
        }
    }

    /** Evaluate the Arithmetic Expression */
    @FXML
    protected void evaluateExpression() throws IOException {
        try {
            ExpressionTree ET = new ExpressionTree();
            ExpressionTree.Node expression = ET.buildETWithInfix(operations.getText());
            result.setText(String.valueOf(ET.evaluateExpressions(expression)));
            FileWriter fileWriter = new FileWriter("src/main/java/com/zkurdya/javafxcalculator/Calculation History.txt",true);
            fileWriter.write(operations.getText() + " = " + result.getText() + "\n");
            fileWriter.close();
            operations.setText("");
        } catch (RuntimeException runtimeException){
            operations.setText("");
            result.setText("Invalid Operation");
        }
    }

    /** Preform a backspace */
    @FXML
    protected void backSpace() {
        char temp = operations.getText().charAt(operations.getText().length() - 1);
        operationSelected = temp != '+' && temp != '-' && temp != '*' && temp != '/';
        if (operations.getText().length() != 0)
            operations.setText(operations.getText().substring(0, operations.getText().length() - 1));
    }
}
// By: Zaki Kurdya