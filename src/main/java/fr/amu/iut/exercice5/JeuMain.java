package fr.amu.iut.exercice5;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class JeuMain extends Application {

    private Scene scene;
    private BorderPane root;
    private Stage primaryStage;
    public static List<Obstacle> obstacles = new ArrayList<>();
    private Label lblTimer = new Label();
    private int tempsRestant = 20; // Temps en secondes
    private Timeline timeline;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Afficher l'écran d'accueil
        PageAccueil accueil = new PageAccueil(primaryStage);
        primaryStage.setScene(accueil.getScene());
        primaryStage.setTitle("Pac-Man");
        primaryStage.show();

        // Action du bouton "Jouer"
        accueil.getBtnJouer().setOnAction(event -> lancerJeu());
    }
    private void lancerJeu() {
         root = new BorderPane();

        //Acteurs du jeu
        Personnage pacman = new Pacman();
        Personnage fantome = new Fantome();

        // on positionne le fantôme 20 positions vers la droite
        fantome.setLayoutX(20 * 10);


        //panneau du jeu
        Pane jeu = new Pane();
        jeu.setPrefSize(650, 500);

        creerObstacles(jeu); // <---- Ajout de l'appel à creerObstacles

        jeu.getChildren().add(pacman);
        jeu.getChildren().add(fantome);
        root.setCenter(jeu);

        lblTimer.setText("Temps restant : " + tempsRestant);
        root.setTop(lblTimer); // Ajout du label du timer en haut de la BorderPane
        startTimer();

        //on construit une scene 640 * 480 pixels
        scene = new Scene(root);

        //Gestion du déplacement du personnage
        deplacer(pacman, fantome);

        primaryStage.setTitle("... Pac Man ...");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void creerObstacles(Pane jeu) {
        // Création de quelques obstacles
        Obstacle mur1 = new Obstacle(0, 200, 100, 50);
        Obstacle mur2 = new Obstacle(50, 300, 50, 150);
        Obstacle mur3 = new Obstacle(100, 0, 50, 150);
        Obstacle mur4 = new Obstacle(150, 100, 150, 50);
        Obstacle mur5 = new Obstacle(200, 200, 200, 100);
        Obstacle mur6 = new Obstacle(150, 350, 100, 50);
        Obstacle mur7 = new Obstacle(300, 350, 100, 50);
        Obstacle mur8 = new Obstacle(450, 50, 100, 100);
        Obstacle mur9 = new Obstacle(500, 200, 50, 250);
        Obstacle mur10 = new Obstacle(550, 400, 50, 50);

        // Ajout des obstacles à la liste statique
        obstacles.add(mur1);
        obstacles.add(mur2);
        obstacles.add(mur3);
        obstacles.add(mur4);
        obstacles.add(mur5);
        obstacles.add(mur6);
        obstacles.add(mur7);
        obstacles.add(mur8);
        obstacles.add(mur9);
        obstacles.add(mur10);

        // Ajout des obstacles au panneau de jeu pour les afficher
        jeu.getChildren().addAll(obstacles);
    }

    private void startTimer() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            tempsRestant--;
            lblTimer.setText("Temps restant : " + tempsRestant);
            if (tempsRestant <= 0) {
                timeline.stop();
                afficherResultat(false); // Fantôme a perdu
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    private void stopTimer() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    private void afficherResultat(boolean fantomeGagne) {
        String message = fantomeGagne ? "Le fantôme a gagné !" : "Le fantôme a perdu !";
        Label lblResultat = new Label(message);
        lblResultat.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: " + (fantomeGagne ? "red" : "green") + ";");
        root.setCenter(lblResultat);
    }


    /**
     * Permet de gérer les événements de type clavier, pression des touches
     * pour le j1(up,down, right, left), pour le j2( z,q,s,d)
     *
     * @param j1
     * @param j2
     */
    private void deplacer(Personnage j1, Personnage j2) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            double prevXJ1 = j1.getLayoutX();
            double prevYJ1 = j1.getLayoutY();
            double prevXJ2 = j2.getLayoutX();
            double prevYJ2 = j2.getLayoutY();

            switch (event.getCode()) {
                case LEFT:
                    j1.deplacerAGauche();
                    break;
                case RIGHT:
                    j1.deplacerADroite(scene.getWidth());
                    break;
                case UP:
                    j1.deplacerEnHaut(); // Ajout pour le déplacement vers le haut de j1
                    break;
                case DOWN:
                    j1.deplacerEnBas(scene.getHeight()); // Ajout pour le déplacement vers le bas de j1
                    break;
                case Z:
                    j2.deplacerEnHaut();
                    break;
                case S:
                    j2.deplacerEnBas(scene.getHeight());
                    break;
                case Q:
                    j2.deplacerAGauche();
                    break;
                case D:
                    j2.deplacerADroite(scene.getWidth());
                    break;

            }
// Collision et repositionnement (séparé du déplacement)
            boolean collisionJ1 = false;
            for (Obstacle obstacle : obstacles) {
                if (j1.getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
                    collisionJ1 = true;
                    break;
                }
            }
            if (collisionJ1) {
                j1.setLayoutX(prevXJ1);
                j1.setLayoutY(prevYJ1);
            }

            boolean collisionJ2 = false;
            for (Obstacle obstacle : obstacles) {
                if (j2.getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
                    collisionJ2 = true;
                    break;
                }
            }
            if (collisionJ2) {
                j2.setLayoutX(prevXJ2);
                j2.setLayoutY(prevYJ2);
            }

            if (j1.estEnCollision(j2))
                //stopTimer();
                //afficherResultat(true);
                primaryStage.close();

        });
    }
}

