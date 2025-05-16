package fr.amu.iut.exemple1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CalculatorView view = new CalculatorView();

        Scene scene = new Scene(view);

        primaryStage.setTitle("Calculator App");
        primaryStage.setScene( scene );
        primaryStage.setWidth( 250 );
        primaryStage.setHeight( 160 );
        primaryStage.show();
    }
}
