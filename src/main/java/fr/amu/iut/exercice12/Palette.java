package fr.amu.iut.exercice2;

import fr.amu.iut.exercice12.CustomButton;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

@SuppressWarnings("Duplicates")
public class Palette extends Application {

    private Label texteDuHaut;

    private CustomButton vert;
    private CustomButton rouge;
    private CustomButton bleu;

    private CustomButton sourceOfEvent;

    private BorderPane root;
    private Pane panneau;
    private HBox boutons;
    private VBox bas; // Initialisé mais pourrait être refactorisé
    private Label texteDuBas;

    private EventHandler<ActionEvent> gestionnaireEvenement;

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();

        texteDuHaut = new Label();
        texteDuHaut.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(texteDuHaut, Pos.CENTER);
        texteDuBas = new Label("Cliquez sur un bouton !"); // Initialisation de texteDuBas avec un message de départ

        panneau = new Pane();
        panneau.setPrefSize(400, 200);
        panneau.setStyle("-fx-background-color: #F0F0F0;"); // Couleur de fond initiale, par exemple gris clair

        boutons = new HBox(10);
        boutons.setAlignment(Pos.CENTER);
        boutons.setPadding(new Insets(10, 5, 10, 5));

        vert = new CustomButton("Vert", "#31BCA4");
        rouge = new CustomButton("Rouge", "#F21411");
        bleu = new CustomButton("Bleu", "#3273A4");

        gestionnaireEvenement = (event) -> {
            sourceOfEvent = (CustomButton) event.getSource();
        };

        vert.setOnAction(gestionnaireEvenement);
        rouge.setOnAction(gestionnaireEvenement);
        bleu.setOnAction(gestionnaireEvenement);

        boutons.getChildren().addAll(vert, rouge, bleu);

        VBox bottomContainer = new VBox(5);
        bottomContainer.setAlignment(Pos.CENTER);
        bottomContainer.getChildren().addAll(boutons, texteDuBas); // Ajout de texteDuBas sous les boutons

        root.setCenter(panneau);
        root.setTop(texteDuHaut);
        root.setBottom(bottomContainer);

        // *** DÉBUT DE L'IMPLÉMENTATION MODIFIÉE DU LISTENER nbClicsListener ***
        ChangeListener<Number> nbClicsListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (sourceOfEvent != null) {
                    // Mise à jour de texteDuHaut
                    texteDuHaut.setText("Nombre de clics sur le " + sourceOfEvent.getText() + " : " + newValue.intValue());

                    // Mise à jour du style du panneau
                    panneau.setStyle("-fx-background-color: " + sourceOfEvent.getCouleur() + ";");

                    // *** NOUVEAUTÉ : Mise à jour de texteDuBas ***
                    // On peut par exemple afficher un message de confirmation du dernier clic
                    texteDuBas.setText("Dernier bouton cliqué : " + sourceOfEvent.getText() +
                            " (Total : " + newValue.intValue() + " clics)");
                    // Ou simplement :
                    // texteDuBas.setText("Vous avez cliqué sur " + sourceOfEvent.getText() + ".");
                }
            }
        };

        // Associer le listener aux compteurs nbClics des 3 boutons
        vert.nbClicsProperty().addListener(nbClicsListener);
        rouge.nbClicsProperty().addListener(nbClicsListener);
        bleu.nbClicsProperty().addListener(nbClicsListener);
        // *** FIN DE L'IMPLÉMENTATION MODIFIÉE DU LISTENER nbClicsListener ***

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Palette de Couleurs");
        primaryStage.setWidth(500);
        primaryStage.setHeight(300);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}