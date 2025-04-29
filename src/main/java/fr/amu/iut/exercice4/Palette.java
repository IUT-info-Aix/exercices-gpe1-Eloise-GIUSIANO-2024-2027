package fr.amu.iut.exercice4;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Label label;
    private Pane panneau;
    private HBox bas;

    private Label helloLabel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Création d'un conteneur VBox avec ses éléments centrés
        VBox vbox = new VBox();
        vbox.setAlignment( Pos.CENTER );

        // Création et ajout du label au conteneur
        Label helloLabel = new Label("Bonjour !");
        vbox.getChildren().add( helloLabel );

        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color: black;");
        canvas.setPrefSize(400,200);

        HBox hbox = new HBox(8);// spacing = 8
        hbox.setAlignment( Pos.CENTER );
        hbox.setStyle("-fx-background-color: lightgray;");
        Button vert = new Button("Vert");
        Button rouge = new Button("Rouge");
        Button bleu = new Button("Bleu");

        hbox.getChildren().addAll(vert, rouge, bleu);

        vert.setOnAction(event -> {
            canvas.setStyle("-fx-background-color: #31bc77;");
            nbVert++;
            helloLabel.setText( "Vert choisi "+ nbVert + " fois");
        });

        rouge.setOnAction(event -> {
            canvas.setStyle("-fx-background-color: #9e0523;");
            nbRouge++;
            helloLabel.setText( "Rouge choisi "+ nbRouge + " fois");
        });

        bleu.setOnAction(event -> {
            canvas.setStyle("-fx-background-color: #4f86e4;");
            nbBleu++;
            helloLabel.setText( "Bleu choisi "+ nbBleu + " fois");
        });

        // Création de la scene
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(vbox);       // vbox en haut
        borderPane.setCenter(canvas);   // canvas au centre
        borderPane.setBottom(hbox);     // hbox en bas

        Scene scene = new Scene(borderPane);


        // Ajout de la scene à la fenêtre
        primaryStage.setScene( scene );

        primaryStage.setTitle("Hello application");
        primaryStage.setWidth(400);
        primaryStage.setHeight(200);
        primaryStage.show();
    }
}

