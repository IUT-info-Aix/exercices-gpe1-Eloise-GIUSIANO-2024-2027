package fr.amu.iut.exercice14;

import fr.amu.iut.exercice4.Personne;
import javafx.beans.Observable;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

@SuppressWarnings("Duplicates")
public class MainPersonnes {

    private static SimpleListProperty<Personne> lesPersonnes;
    private static IntegerProperty ageMoyen;
    private static IntegerProperty nbParisiens;

    private static IntegerBinding calculAgeMoyen;
    private static IntegerBinding calculnbParisiens; // Déclaration du binding

    public static void main(String[] args) {

        // Initialisation de la liste avec un extracteur pour écouter les propriétés des Personnes
        lesPersonnes = new SimpleListProperty<>(FXCollections.observableArrayList(
                personne -> new Observable[]{
                        personne.ageProperty(),          // Écoute les changements d'âge
                        personne.villeDeNaissanceProperty() // NOUVEAU: Écoute les changements de ville de naissance
                }
        ));

        ageMoyen = new SimpleIntegerProperty(0);
        nbParisiens = new SimpleIntegerProperty(0); // Initialisation de nbParisiens

        // --- Définition et liaison du binding pour l'âge moyen ---
        calculAgeMoyen = new IntegerBinding() {
            {
                this.bind(lesPersonnes);
            }

            @Override
            protected int computeValue() {
                if (lesPersonnes.isEmpty()) {
                    return 0;
                }
                int sommeDesAges = 0;
                for (Personne p : lesPersonnes) {
                    sommeDesAges += p.getAge();
                }
                return sommeDesAges / lesPersonnes.size();
            }
        };
        ageMoyen.bind(calculAgeMoyen);

        // --- Définition et liaison du binding pour le nombre de Parisiens ---
        calculnbParisiens = new IntegerBinding() { // Création du second binding
            {
                this.bind(lesPersonnes); // Le binding dépend de la liste (qui observe les villes de naissance)
            }

            @Override
            protected int computeValue() {
                int count = 0;
                for (Personne p : lesPersonnes) {
                    if ("Paris".equals(p.getVilleDeNaissance())) {
                        count++;
                    }
                }
                return count;
            }
        };
        nbParisiens.bind(calculnbParisiens); // Lier la propriété nbParisiens au binding

        question1();
        System.out.println("\n-----------------------------------------------\n");
        question2();
    }

    public static void question1() {
        System.out.println("1 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        Personne pierre = new Personne("Pierre", 20);
        lesPersonnes.add(pierre);
        System.out.println("2 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        Personne paul = new Personne("Paul", 40);
        lesPersonnes.add(paul);
        System.out.println("3 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(jacques);
        System.out.println("4 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        paul.setAge(100);
        System.out.println("5 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        lesPersonnes.remove(paul);
        System.out.println("6 - L'age moyen est de " + ageMoyen.getValue() + " ans");
    }

    public static void question2()  {
        System.out.println("--- Test du nombre de Parisiens ---");
        System.out.println("Initialisation question 2. Il y a " + nbParisiens.getValue() + " parisiens");
        // Les personnes ajoutées dans question1 sont toujours là
        // Pierre: Paris (par défaut), Jacques: Paris (par défaut)
        // nbParisiens devrait être 2

        lesPersonnes.get(0).setVilleDeNaissance("Marseille"); // Pierre (index 0)
        System.out.println("Après changement de Pierre en Marseille. Il y a " + nbParisiens.getValue() + " parisiens");

        lesPersonnes.get(1).setVilleDeNaissance("Montpellier"); // Jacques (index 1)
        System.out.println("Après changement de Jacques en Montpellier. Il y a " + nbParisiens.getValue() + " parisiens");

        for (Personne p : lesPersonnes)
            p.setVilleDeNaissance("Paris"); // Tous redeviennent Parisiens
        System.out.println("Après que tout le monde est devenu Parisien. Il y a " + nbParisiens.getValue() + " parisiens");
    }

}