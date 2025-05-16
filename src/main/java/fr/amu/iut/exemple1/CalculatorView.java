package fr.amu.iut.exemple1;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class CalculatorView extends Pane {

    public CalculatorView() throws IOException {

        // chargement des composants de la fenêtre
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CalculatorView.fxml"));
        VBox root = loader.load();

        // Ajout des composants dans la fenêtre
        this.getChildren().add(root);
    }
}
