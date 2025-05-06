package fr.amu.iut.exercice9;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animation extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        CustomButton customButton = new CustomButton();
        root.setCenter(customButton);
        Scene scene = new Scene(root, 400, 400);

        Duration duration = Duration.millis(1500);
        TranslateTransition transition1 = new TranslateTransition(duration, customButton);
        transition1.setByY(-150);
        TranslateTransition transition2 = new TranslateTransition(duration, customButton);
        transition2.setByX(150);
        TranslateTransition transition3 = new TranslateTransition(duration, customButton);
        transition3.setByY(300);
        TranslateTransition transition4 = new TranslateTransition(duration, customButton);
        transition4.setByX(-300);
        TranslateTransition transition5 = new TranslateTransition(duration, customButton);
        transition5.setByY(-300);
        TranslateTransition transition11 = new TranslateTransition(duration, customButton);
        transition11.setByX(150);

        TranslateTransition transition6 = new TranslateTransition(duration, customButton);
        transition6.setByY(300);
        TranslateTransition transition7 = new TranslateTransition(duration, customButton);
        transition7.setByX(300);
        TranslateTransition transition8 = new TranslateTransition(duration, customButton);
        transition8.setByY(-300);
        TranslateTransition transition9 = new TranslateTransition(duration, customButton);
        transition9.setByX(-150);
        TranslateTransition transition10 = new TranslateTransition(duration, customButton);
        transition10.setByY(150);
        TranslateTransition transition12 = new TranslateTransition(duration, customButton);
        transition12.setByX(-150);

        SequentialTransition st = new SequentialTransition(transition1, transition2, transition3, transition4, transition5, transition11,  transition12, transition6, transition7, transition8, transition9, transition10);
        st.play();

        customButton.setOnMousePressed(mouseEvent -> transition6.play());

        primaryStage.setTitle("Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}