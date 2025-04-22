package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class FenetreLogiciel extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Création du conteneur principal (BorderPane)
        BorderPane root = new BorderPane();
        root.setPrefSize(500, 400); // Taille initiale (facultatif)

        // Création du menu & sous items
        MenuItem menuItemNew = new MenuItem("New");
        MenuItem menuItemOpen = new MenuItem("Open");
        MenuItem menuItemSave = new MenuItem("Save");
        MenuItem menuItemClose = new MenuItem("Close");

        MenuItem menuItemCut = new MenuItem("Cut");
        MenuItem menuItemCopy = new MenuItem("Copy");
        MenuItem menuItemPaste = new MenuItem("Paste");

        Menu menuFile = new Menu("File");
        menuFile.getItems().addAll(menuItemNew, menuItemOpen, menuItemSave, menuItemClose);

        Menu menuEdit = new Menu("Edit");
        menuEdit.getItems().addAll(menuItemCut, menuItemCopy, menuItemPaste);

        Menu menuHelp = new Menu("Help");
        MenuBar menuBar = new MenuBar(menuFile, menuEdit, menuHelp);

        // Lie la largeur de la barre de menu à la largeur du BorderPane
        menuBar.prefWidthProperty().bind(root.widthProperty());
        menuBar.minWidthProperty().bind(root.widthProperty());
        menuBar.maxWidthProperty().bind(root.widthProperty());

        // **Positionne la barre de menu en haut du BorderPane**
        root.setTop(menuBar);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(200));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label boutonsLabel = new Label("Boutons :");
        Button bouton1 = new Button("Bouton 1");
        Button bouton2 = new Button("Bouton 2");
        Button bouton3 = new Button("Bouton 3");
        VBox leftButtons = new VBox(5, boutonsLabel, bouton1, bouton2, bouton3);
        GridPane.setConstraints(leftButtons, 0, 0, 1, 3); // Col 0, Lignes 0-2

        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField();
        GridPane.setConstraints(nameLabel, 1, 0);     // Col 1, Ligne 0
        GridPane.setConstraints(nameTextField, 2, 0); // Col 2, Ligne 0

        Label emailLabel = new Label("Email:");
        TextField emailTextField = new TextField();
        GridPane.setConstraints(emailLabel, 1, 1);    // Col 1, Ligne 1
        GridPane.setConstraints(emailTextField, 2, 1); // Col 2, Ligne 1

        Label passwordLabel = new Label("Password:");
        PasswordField passwordTextField = new PasswordField();
        GridPane.setConstraints(passwordLabel, 1, 2);       // Col 1, Ligne 2
        GridPane.setConstraints(passwordTextField, 2, 2);   // Col 2, Ligne 2

        Button submitButton = new Button("Submit");
        Button cancelButton = new Button("Cancel");
        HBox actionButtons = new HBox(10, submitButton, cancelButton);
        GridPane.setConstraints(actionButtons, 2, 3); // Col 2, Ligne 3
        GridPane.setHalignment(actionButtons, HPos.RIGHT);

        gridPane.getChildren().addAll(leftButtons, nameLabel, nameTextField, emailLabel, emailTextField, passwordLabel, passwordTextField, actionButtons);

        root.setCenter(gridPane);
        BorderPane.setAlignment(gridPane, Pos.CENTER);

        // Création de la scène avec le BorderPane comme racine
        Scene scene = new Scene(root, 800, 600);

        // Configuration de la fenêtre principale
        primaryStage.setScene(scene);
        primaryStage.setTitle("Application avec Menu");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}