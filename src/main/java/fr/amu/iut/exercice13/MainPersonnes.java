package fr.amu.iut.exercice3;

import fr.amu.iut.exercice13.Personne;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.Observable;
import javafx.util.Callback;

@SuppressWarnings("Duplicates")
public class MainPersonnes  {

    private static ObservableList<Personne> lesPersonnes;

    // Déclaration du nouvel écouteur
    private static ListChangeListener<Personne> plusieursChangementsListener;

    public static void main(String[] args) {

        // Instanciation de la liste avec observation des propriétés internes
        lesPersonnes = FXCollections.observableArrayList(personne -> new Observable[] {personne.ageProperty()});

        // Implémentation du nouvel écouteur pour gérer plusieurs changements
        plusieursChangementsListener = change -> {
            System.out.println("--- Début du traitement d'un lot de changements ---");

            while (change.next()) {
                // Si des éléments ont été ajoutés
                if (change.wasAdded()) {
                    // Pour chaque personne ajoutée dans la sous-liste
                    for (Personne p : change.getAddedSubList()) {
                        System.out.println("  -> Changement détecté : Personne ajoutée : " + p.getNom());
                        p.ageProperty().addListener((observable, oldValue, newValue) -> {
                            System.out.println("    -> " + p.getNom() + " a maintenant " + newValue.intValue() + " ans (anciennement " + oldValue.intValue() + " ans).");
                        });
                    }
                }
                // Si des éléments ont été supprimés
                if (change.wasRemoved()) {
                    // Pour chaque personne supprimée dans la sous-liste
                    for (Personne p : change.getRemoved()) {
                        System.out.println("  -> Changement détecté : Personne supprimée : " + p.getNom());
                    }
                }
                // Si des éléments ont été remplacés
                if (change.wasReplaced()) {
                    System.out.println("  -> Changement détecté : Éléments remplacés de l'index " + change.getFrom() + " à " + change.getTo());
                }

                if (change.wasUpdated()) {
                    System.out.println("  -> Changement détecté : Propriétés internes d'un élément mises à jour à l'index " + change.getFrom());
                    // Exemple pour voir la personne affectée, même si le ChangeListener de l'âge est plus précis pour l'âge
                    if (!lesPersonnes.isEmpty() && change.getFrom() < lesPersonnes.size()) {
                        System.out.println("    -> L'élément à l'index " + change.getFrom() + " (" + lesPersonnes.get(change.getFrom()).getNom() + ") a été mis à jour.");
                    }
                }
                // Si les éléments ont été déplacés (drag and drop par exemple)
                if (change.wasPermutated()) {
                    System.out.println("  -> Changement détecté : Éléments permutés.");
                }
            }
            // Message pour indiquer la fin du traitement d'un lot de changements
            System.out.println("--- Fin du traitement du lot de changements ---\n");
        };

        lesPersonnes.addListener(plusieursChangementsListener);

        // Appel de la méthode question5() pour tester le code
        question5();
    }

    public static void question1() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        System.out.println("Ajout de Pierre, Paul, Jacques...");
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
    }

    public static void question2() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        System.out.println("\nAjout de Pierre, Paul, Jacques, puis suppression de Paul...");
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        lesPersonnes.remove(paul); // Ici Paul sera supprimé
        }

    public static void question3() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        System.out.println("\n--- Exécution de question3() ---");
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        System.out.println("Modification de l'âge de Paul à 5 ans...");
        paul.setAge(5); // C'est cette ligne qui devrait déclencher le ChangeListener
        }

    public static void question5() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.addAll(pierre, paul, jacques);
        for (Personne p : lesPersonnes) p.setAge(p.getAge()+10);
        lesPersonnes.removeAll(paul, pierre);
    }


}