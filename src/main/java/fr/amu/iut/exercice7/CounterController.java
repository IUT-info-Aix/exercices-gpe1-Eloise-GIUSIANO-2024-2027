package fr.amu.iut.exercice7;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class CounterController implements Initializable {

    int counter = 0;

    public void increment() {
        counter++; // Incrémente la valeur du compteur
        counterLabel.setText(String.valueOf(counter));
    }

    public void decrement() {
        counter--;
        counterLabel.setText(String.valueOf(counter));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing CounterController...");
        counterLabel.setText("0");
    }

    @FXML
    private Label counterLabel;

    @FXML
    private Button decrementButton;

    @FXML
    private Button incrementButton;

}