package fr.amu.iut.exercice13;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Personne {

    private String nom;
    // Changement : age est maintenant une IntegerProperty
    private IntegerProperty age;
    private StringProperty villeDeNaissance;

    public Personne(String nom, int age) {
        this.nom = nom;
        // Instanciation de la IntegerProperty
        this.age = new SimpleIntegerProperty(age);
        this.villeDeNaissance = new SimpleStringProperty("Paris");
    }

    public String getNom() {
        return nom;
    }

    // Méthode pour définir l'âge via la propriété
    public void setAge(int age) {
        this.age.set(age); // Utilise .set() pour changer la valeur de la propriété
    }

    // Méthode pour obtenir l'âge via la propriété
    public int getAge() {
        return this.age.get(); // Utilise .get() pour obtenir la valeur de la propriété
    }

    // Nouvelle méthode pour accéder à la propriété de l'âge
    public IntegerProperty ageProperty() {
        return age;
    }

}