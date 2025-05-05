package fr.amu.iut.exercice5;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PageAccueil {
    private Stage primaryStage;
    private Scene scene;
    private Button btnJouer;
    private Label titre;
    private Label Regle;
    private Label Touches;
    private Label Touches2;

    public PageAccueil(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initialiser();
    }
private void initialiser() {
    titre = new Label("Bienvenue sur Pacman");
    titre.setStyle("-fx-font-size: 48px; -fx-font-weight: bold;");

    Regle = new Label("Les Regles: Le fantome a 10s pour attrapper le pacman, si il n'y arrive pas il a perdu.");
    Regle.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");

    Touches = new Label("Pacman: fleches ");
    Touches.setStyle("-fx-font-size: 10px; -fx-font-weight: bold;");

    Touches2 = new Label("Fantome: zqsd");
    Touches2.setStyle("-fx-font-size: 10px; -fx-font-weight: bold;");

    btnJouer = new Button("Jouer");
    btnJouer.setStyle("-fx-font-size: 24px;");

    VBox root = new VBox(20, titre, Regle, Touches, Touches2, btnJouer); // Espacement de 20 pixels
    root.setAlignment(Pos.CENTER);

    scene = new Scene(root, 640, 480); // Même dimensions que le jeu
}

public Scene getScene() {
    return scene;
}

public Button getBtnJouer() {
    return btnJouer;
}
}
