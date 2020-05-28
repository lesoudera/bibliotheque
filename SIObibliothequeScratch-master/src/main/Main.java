/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import bibliotheque.App;
import bibliotheque.Bibliotheque;

/**
 *
 * @author Damien
 */
public class Main {

    /*
    Méthode main de la Classe Main. Elle est ici prévue pour être la classe principale du projet.
    (il faut vérifier dans les propriétés du projet qu'elle est bien sélectionnée)
    C'est à dire que lorsqu'on exécute le projet c'est cette méthode "public static void main" qui
    est récupérée et exécutée.
    
    Lorsque l'on souhaite exécuter une autre psvm on utilisera la fonctionnalité "run file" depuis
    la Classe contenant la psvm à éxécuter.
     */
    public static void main(String[] args) {
//        Bibliotheque biblio = new Bibliotheque();
//        biblio.runBibliotheque();

        App app = new App();
        app.runBibliothequeIHM();
    }
}
