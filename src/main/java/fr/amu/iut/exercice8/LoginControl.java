package fr.amu.iut.exercice8;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane {

    @FXML
    private TextField userId;

    @FXML
    private PasswordField pwd;

    @FXML
    private Button handleOkButton;

    @FXML
    private Button handleCancelButton;

    @FXML
    private Label welcomeText;

    @FXML
    private void OkButton() {
        // Ajoutez ici la logique à exécuter lorsque le bouton "Ok" est cliqué
        String user = userId.getText();
        String password = pwd.getText();
        welcomeText.setText("Bienvenue, " + user + "!"); // Exemple simple
        System.out.println("Bouton OK cliqué!");
        System.out.println("Identifiant: " + user);
        System.out.println("Mot de passe: " + password);
    }

    @FXML
    private void CancelButton() {
        // Ajoutez ici la logique à exécuter lorsque le bouton "Cancel" est cliqué
        userId.clear();
        pwd.clear();
        welcomeText.setText("Welcome"); // Réinitialise le texte d'accueil
        System.out.println("Bouton Cancel cliqué!");
    }
}