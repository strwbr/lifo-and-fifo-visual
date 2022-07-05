package com.example.lifo_and_fifo_visual.controllers;

import com.example.lifo_and_fifo_visual.FIFO;
import com.example.lifo_and_fifo_visual.LIFO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class Controller {
    @FXML
    private TextField textField;

    @FXML
    private TextField currentLifoValueTF;
    @FXML
    private TextField currentFifoValueTF;

    @FXML
    private VBox vBoxLifo;
    @FXML
    private VBox vBoxFifo;

    private final LIFO lifo = new LIFO();
    private final FIFO fifo = new FIFO();
    private final ArrayList<TextField> lifoFields = new ArrayList<>();
    private final ArrayList<TextField> fifoFields = new ArrayList<>();

    private int i; // счетчик нажатий на кнопку STEP

    public Controller() {
    }

    public void okBtnAction(ActionEvent actionEvent) {
        String text = this.textField.getText();
        if (isEndOfInput(text) && isLatinCharacter(text) && !isNumber(text) && isCharacter(text)) {
            lifo.push(text);
            fifo.enqueue(text);

            lifoFields.add(0, createElement(text));
            fifoFields.add(createElement(text));
            printAllElements();
            printCurrentValue();
            this.textField.deleteText(0, text.length());
        }
    }

    private boolean isCharacter(String inputStr) {
        if (inputStr.length() == 1)
            return true;
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Допускается только символ английского алфавита");
            alert.setHeaderText(null);
            alert.setTitle("Неправильный символ");
            alert.show();
            this.textField.deleteText(0, inputStr.length());
            return false;
        }
    }

    private boolean isEndOfInput(String inputStr) {
        if (inputStr.equals("*")) {
            this.textField.setEditable(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Вы ввели специальный символ. Ввод новых элементов недоступен");
            alert.setHeaderText(null);
            alert.setTitle("Ввод недоступен");
            alert.show();
            return false;
        }
        return true;
    }

    private boolean isLatinCharacter(String inputStr) {
        if (!inputStr.matches("^[a-zA-Z0-9]+$")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Допускается только символ английского алфавита");
            alert.setHeaderText(null);
            alert.setTitle("Неправильный символ");
            alert.show();
            this.textField.deleteText(0, inputStr.length());
            return false;
        }
        return true;
    }

    private boolean isNumber(String inputStr) {
        for (char c : inputStr.toCharArray()) {
            if (Character.isDigit(c)) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Допускается только символ английского алфавита");
                alert.setHeaderText(null);
                alert.setTitle("Неправильный символ");
                alert.show();
                this.textField.deleteText(0, inputStr.length());
                return true;
            }
        }
        return false;
    }

    private TextField createElement(String text) {
        TextField textField = new TextField(text);
        textField.setEditable(false);
        textField.setAlignment(Pos.CENTER);
        textField.setStyle("-fx-text-fill: #22222E; -fx-border-color: #706F8E; -fx-background-color: #ADA9BA;");
        textField.setFont(Font.font("System", FontWeight.BOLD, 18));
        return textField;
    }

    private void printElement(TextField textField1, TextField textField2) {
        vBoxLifo.getChildren().add(textField1);
        vBoxFifo.getChildren().add(textField2);
    }

    private void printAllElements() {
        vBoxLifo.getChildren().clear();
        vBoxFifo.getChildren().clear();
        for (int i = 0; i < lifoFields.size(); i++) {
            printElement(lifoFields.get(i), fifoFields.get(i));
        }
    }

    public void deleteBtnAction(ActionEvent actionEvent) {
        if (lifo.pop() && fifo.dequeue()) {
            lifoFields.remove(0);
            fifoFields.remove(0);
            printAllElements();
            printCurrentValue();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ошибка при удалении элемента\nОчередь пуста. Сначала добавьте элемент", ButtonType.OK);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    private void printCurrentValue() {
        String valueLifo = lifo.peek();
        String valueFifo = fifo.peek();
        if (valueLifo == null && valueFifo == null) return;
        this.currentLifoValueTF.setText(valueLifo);
        this.currentFifoValueTF.setText(valueFifo);
    }

    public void stepBtnAction(ActionEvent actionEvent) {
        this.textField.setEditable(false);
        i++;
        String value = lifo.getByNum(i);
        if (value != null)
            this.currentLifoValueTF.setText(value);
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Элементов нет");
            alert.setHeaderText(null);
            alert.show();
        }
    }
}