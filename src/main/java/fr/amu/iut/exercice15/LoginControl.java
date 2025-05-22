package fr.amu.iut.exercice15;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;

// Ajoute l'import pour URL et ResourceBundle si tu ne l'as pas déjà (nécessaire pour la signature de initialize)
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable; // Importe l'interface Initializable

// Implémente l'interface Initializable
public class LoginControl extends GridPane implements Initializable {
    @FXML
    private TextField userId;

    @FXML
    private PasswordField pwd;

    // Assure-toi que ces fx:id correspondent à ton FXML (okBtn, cancelBtn ou okButton, cancelButton)
    // D'après ta dernière FXML, ils sont okBtn et cancelBtn.
    @FXML
    private Button okBtn;
    @FXML
    private Button cancelBtn;


    // La méthode initialize doit avoir cette signature précise pour être appelée par FXMLLoader
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Appelle createBindings() ici, après que tous les @FXML soient injectés
        createBindings();
    }

    private void createBindings() {
        // 1. Le champ du mot de passe ne soit pas éditable si le nom de l’utilisateur fait moins de 6 caractères
        pwd.editableProperty().bind(userId.textProperty().length().greaterThanOrEqualTo(6));

        // 2. Le bouton cancel ne soit pas cliquable si les deux champs sont vides
        // Utilise cancelBtn, comme dans ton FXML
        cancelBtn.disableProperty().bind(
                Bindings.createBooleanBinding(
                        () -> userId.getText().isEmpty() && pwd.getText().isEmpty(),
                        userId.textProperty(),
                        pwd.textProperty()
                )
        );

        // 3. Le bouton ok ne soit pas cliquable tant que le mot de passe n’a pas au moins 8 caractères,
        //    et ne contient pas au moins une majuscule et un chiffre.
        BooleanBinding passwordValid = Bindings.createBooleanBinding(
                () -> {
                    String password = pwd.getText();
                    boolean hasUppercase = false;
                    boolean hasDigit = false;

                    if (password.length() < 8) {
                        return false;
                    }

                    for (char c : password.toCharArray()) {
                        if (Character.isUpperCase(c)) {
                            hasUppercase = true;
                        }
                        if (Character.isDigit(c)) {
                            hasDigit = true;
                        }
                    }
                    return hasUppercase && hasDigit;
                },
                pwd.textProperty()
        );
        // Utilise okBtn, comme dans ton FXML
        okBtn.disableProperty().bind(passwordValid.not());
    }

    @FXML
    private void okClicked() {
        System.out.print(userId.getText() + " ");
        for (char c : pwd.getText().toCharArray()) {
            System.out.print("*");
        }
        System.out.println();
    }

    @FXML
    private void cancelClicked() {
        userId.setText("");
        pwd.setText("");
    }
}