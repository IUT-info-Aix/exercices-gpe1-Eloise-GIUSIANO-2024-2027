package fr.amu.iut.exemple1;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class CalculatorViewController {
    @FXML
    private Label resultLabel;
    @FXML
    private TextField num2Field;
    @FXML
    private TextField num1Field;

    public void initialize() {
        // récupération des propriétés associées aux champs de saisie
        StringProperty num1Str = num1Field.textProperty();
        StringProperty num2Str = num2Field.textProperty();

        // conversion str -> double et liaison avec les textes saisis
        StringConverter<Number> converter = new NumberStringConverter();
        DoubleProperty num1 = new SimpleDoubleProperty();
        DoubleProperty num2 = new SimpleDoubleProperty();
        Bindings.bindBidirectional(num1Str,num1,converter);
        Bindings.bindBidirectional(num2Str,num2,converter);

        // Calcul de la somme et liaison avec le label
        DoubleBinding sum = num1.add(num2);
        resultLabel.textProperty().bind(Bindings.format("Résultat : %.2f", sum));
    }
}
