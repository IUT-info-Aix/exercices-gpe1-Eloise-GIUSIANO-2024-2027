package fr.amu.iut.exercice5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class JeuMain extends Application {

    private Scene scene;
    private BorderPane root;
    private Stage primaryStage;
    public static List<Obstacle> obstacles = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        root = new BorderPane();

        //Acteurs du jeu
        Personnage pacman = new Pacman();
        Personnage fantome = new Fantome();

        // on positionne le fantôme 20 positions vers la droite
        fantome.setLayoutX(20 * 10);


        //panneau du jeu
        Pane jeu = new Pane();
        jeu.setPrefSize(640, 480);

        creerObstacles(jeu); // <---- Ajout de l'appel à creerObstacles

        jeu.getChildren().add(pacman);
        jeu.getChildren().add(fantome);
        root.setCenter(jeu);
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
        Obstacle murHaut = new Obstacle(300, 200, 200, 200);
        Obstacle murVertical = new Obstacle(100, 100, 100, 150);
        Obstacle murBas = new Obstacle(100, 200, 100, 200);

        // Ajout des obstacles à la liste statique
        obstacles.add(murHaut);
        obstacles.add(murVertical);
        obstacles.add(murBas);

        // Ajout des obstacles au panneau de jeu pour les afficher
        jeu.getChildren().addAll(obstacles);
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
                primaryStage.close();
        });
    }
}

