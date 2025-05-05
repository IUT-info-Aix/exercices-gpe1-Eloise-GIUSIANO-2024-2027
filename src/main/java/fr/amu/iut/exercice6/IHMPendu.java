package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class IHMPendu extends Application {

    private Dico dico = new Dico();
    private String motADeviner;
    private int nbVies = 7;
    private Label viesLabel;
    private Label motCacheLabel;
    private TextField propositionTextField;
    private ImageView penduImageView;
    private final String imageUrl = "http://googleusercontent.com/image_collection/image_retrieval/6496299127669612982"; // URL de l'image du pendu
    private GridPane clavierPane;
    private List<Button> lettreBoutons = new ArrayList<>();
    private Button rejouerButton;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Jeu du pendu");

        // Initialize ImageView
        penduImageView = new ImageView(new Image(imageUrl + "/0.png"));
        penduImageView.setFitWidth(150);
        penduImageView.setPreserveRatio(true);

        // Initialize TextField
        propositionTextField = new TextField();
        propositionTextField.setPromptText("Proposez une lettre");
        propositionTextField.setOnAction(event -> gererPropositionViaTextField());
        propositionTextField.setVisible(false); // On cache le TextField

        initialiserJeu(); // Call initialiserJeu AFTER initializing UI elements

        clavierPane = creerClavier();

        rejouerButton = new Button("Rejouer");
        rejouerButton.setOnAction(event -> initialiserJeu());

        HBox controlsBox = new HBox(10);
        controlsBox.setAlignment(Pos.CENTER);
        controlsBox.getChildren().addAll(rejouerButton);

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(viesLabel, motCacheLabel, propositionTextField, penduImageView, clavierPane, controlsBox);

        Scene scene = new Scene(root, 400, 550); // Augmenter la hauteur pour le bouton
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initialiserJeu() {
        System.out.println("La méthode initialiserJeu() a été appelée !");
        motADeviner = dico.getMot();
        System.out.println("Nouveau mot à deviner : " + motADeviner);
        nbVies = 7;
        viesLabel.setText("Vies restantes : " + nbVies); // Mise à jour du texte du Label existant
        viesLabel.setFont(Font.font("Verdana", 16)); // Mise à jour de la police du Label existant

        String nouveauMotCache = afficherMotCache();
        System.out.println("Mot caché généré : " + nouveauMotCache); // Ajout de cette ligne
        motCacheLabel.setText(nouveauMotCache); // Mise à jour du texte du Label existant
        motCacheLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 24)); // Mise à jour de la police du Label existant

        penduImageView.setImage(new Image(imageUrl + "/0.png"));

        if (clavierPane != null) {
            mettreAJourClavier();
        } else {
            clavierPane = creerClavier();
        }

        if (rejouerButton != null) {
            rejouerButton.setDisable(false);
        }
        // Réinitialise l'état du champ de proposition (bien que caché)
        propositionTextField.clear();
        propositionTextField.setDisable(false);
    }

    private void mettreAJourClavier() {
        clavierPane.getChildren().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).setDisable(false);
            }
        });
    }

    private GridPane creerClavier() {
        GridPane clavier = new GridPane();
        clavier.setAlignment(Pos.CENTER);
        clavier.setHgap(5);
        clavier.setVgap(5);
        lettreBoutons.clear(); // Important de vider la liste si le clavier est recréé

        String alphabet = "azertyuiopqsdfghjklmwxcvbn";
        int row = 0;
        int col = 0;

        for (char lettre : alphabet.toCharArray()) {
            Button boutonLettre = new Button(String.valueOf(lettre).toUpperCase());
            boutonLettre.setPrefWidth(30);
            boutonLettre.setOnAction(event -> gererPropositionViaBouton(lettre));
            clavier.add(boutonLettre, col, row);
            lettreBoutons.add(boutonLettre);
            col++;
            if (col % 10 == 0) {
                row++;
                col = 0;
            }
        }
        return clavier;
    }

    private String afficherMotCache() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < motADeviner.length(); i++) {
            sb.append("_ ");
        }
        return sb.toString().trim();
    }

    private void gererPropositionViaTextField() {
        String proposition = propositionTextField.getText().trim().toLowerCase();
        propositionTextField.clear();
        traiterProposition(proposition);
    }

    private void gererPropositionViaBouton(char lettreProposee) {
        traiterProposition(String.valueOf(lettreProposee));
        // Désactiver le bouton cliqué
        for (Button bouton : lettreBoutons) {
            if (bouton.getText().equalsIgnoreCase(String.valueOf(lettreProposee).toUpperCase())) {
                bouton.setDisable(true);
                break;
            }
        }
    }

    private void traiterProposition(String proposition) {
        if (proposition.length() == 1 && Character.isLetter(proposition.charAt(0))) {
            char lettre = proposition.charAt(0);
            ArrayList<Integer> positions = dico.getPositions(lettre, motADeviner);

            if (positions.isEmpty()) {
                nbVies--;
                viesLabel.setText("Vies restantes : " + nbVies);
                penduImageView.setImage(new Image(imageUrl + "/" + (7 - nbVies) + ".png")); // Met à jour l'image du pendu
                if (nbVies == 0) {
                    motCacheLabel.setText("Perdu ! Le mot était : " + motADeviner);
                    desactiverClavier();
                    rejouerButton.setDisable(false);
                }
            } else {
                StringBuilder nouveauMotCache = new StringBuilder(motCacheLabel.getText());
                for (int position : positions) {
                    nouveauMotCache.setCharAt(position * 2, lettre);
                }
                motCacheLabel.setText(nouveauMotCache.toString());
                if (!nouveauMotCache.toString().contains("_")) {
                    motCacheLabel.setText("Gagné ! Le mot était : " + motADeviner);
                    desactiverClavier();
                    rejouerButton.setDisable(false);
                }
            }
        }
    }

    private void desactiverClavier() {
        for (Button bouton : lettreBoutons) {
            bouton.setDisable(true);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}