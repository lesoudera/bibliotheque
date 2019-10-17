/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotheque;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Damien
 */

/*
Nous souhaitons créer une application mimant une bibliothèque, et les services 
associés donc :
 */
public class Bibliotheque {

    /**
     * Notre bibliothèque contient des livres, on va donc avoir besoin d'un
     * attribut où les ranger.
     */
    public ArrayList<Livres> listeLivres = new ArrayList<Livres>();

    /**
     * On veux pouvoir faire fonctionner notre programme de bibliothèque en
     * console, il est donc nécessaire de prévoir une méthode qui permet de
     * tourner en boucle tant qu'on n'a pas décider d'arrêter le programme.
     */
    public void runBibliotheque() {
        /*
        On crée un objet Scanner(System.in) pour récupérer des entrées (inputs) au
        clavier.
         */
        Scanner sc = new Scanner(System.in);
        /*
        Un boolean permettant de guetter si on souhaite continuer ou pas.
         */
        boolean continuer = true;
        /*
        Un entier pour garder le choix. On aurait pu s'en passer, vous pouvez me
        demander si ça vous intéresse.
         */
        int choix;
        /*
        La boucle qui va permettre de relancer le programme tant qu'on ne souhaite
        pas s'arrêter.
         */
        while (continuer) {
            /*
            Le "menu"
             */
            System.out.println("\nChoisissez une option :\n");
            System.out.println("1. Rechercher un livre");
            System.out.println("2. Enregistrer un livre");
            System.out.println("3. Afficher tous les livres");
            System.out.println("4. Arret du programme");
            System.out.println("\nQuel est votre choix ?");
            /*
            Enregistrement du choix
             */
            choix = sc.nextInt();

            /*
            Structure conditionnelle sur le "choix" précédent
             */
            switch (choix) {
                case 1:
                    this.showSearchResult(this.globaSearch(this.asKeyword()));
                    break;
                case 2:
                    this.enregistrerLivre(this.creationLivre());
                    break;
                case 3:
                    this.afficherConsoleAttrListeLivre();
                    break;
                case 4:
                    continuer = false;
                    System.out.println("Arrêt du programme imminent !");
                    break;
                default:
                    System.out.println("J'ai rien compris !");
                    break;
            }
        }
    }

    /**
     * Méthode de recherche globale (tout attribut) à partir d'un mot-clé. Elle
     * va créer une liste des livres qui peuvent correspondre à la recherche et
     * la retourner. On passe en revue chaque attribut et dès qu'un contient le
     * mot-clé de recherche on change l'état d'ajout à la liste de résultat.
     *
     * @param recherche Mot-clé de recherche
     */
    public ArrayList<Livres> globaSearch(String recherche) {
        //Préparation de la liste de résultats
        ArrayList<Livres> resultatDeRecherche = new ArrayList();
        //Boucle sur la liste de livres complète
        for (Livres livre : listeLivres) {
            //Creation (et initialisation éventuelle) du boolean gérant l'ajout
            boolean ajout/* = false*/;
            //Liste de vérifications
            if (this.verifContainStr(livre.getTitre(), recherche)) {
                ajout = true;
            } else if (this.verifContainStr(livre.getAuteur(), recherche)) {
                ajout = true;
            } else if (this.verifContainStr(livre.getRef(), recherche)) {
                ajout = true;
            } else if (this.verifContainStr(livre.getNbrePages(), recherche)) {
                ajout = true;
            } else if (this.verifContainStr(livre.getEdition(), recherche)) {
                ajout = true;
            } else if (this.verifContainStr(livre.getGenre(), recherche)) {
                ajout = true;
            } else if (this.verifContainStr(livre.getAnneeEdition(), recherche)) {
                ajout = true;
            } else if (this.verifContainStr(livre.getLangue(), recherche)) {
                ajout = true;
            } else if (this.verifContainStr(livre.getFormat(), recherche)) {
                ajout = true;
            } else {
                /*
                Pour être "sûr" on refixe le booleen déterminant l'ajout à 
                false dans le cas ou rien ne convient. Si le boolean est initialisé
                à false, cette partie est redondante.
                 */
                ajout = false;
            }
            //ajout à la liste de résultats conditionnel à chaque itération
            if (ajout) {
                resultatDeRecherche.add(livre);
            }
        }//Sortie de la boucle for
        return resultatDeRecherche;
    }

    /**
     * Méthode pour demander un mot-clé et le retourner
     */
    public String asKeyword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel mot-clé souhaitez-vous utiliser ?");
        return sc.nextLine();
    }

    /**
     * Méthode de vérification qu'une String contenant contient une String
     * contenu ignorant la casse
     *
     * @param contenant La String dans laquelle on recherche
     * @param contenu La String que l'on essaie de retrouver
     */
    public boolean verifContainStr(String contenant, String contenu) {
        return contenant.toLowerCase().contains(contenu.toLowerCase());
    }

    /**
     * Affiche dans la console un résultat de recherche donné sous forme de
     * liste.
     *
     * @param liste La liste de recherche à afficher
     */
    public void showSearchResult(ArrayList<Livres> liste) {
        System.out.println("\nRésultat de la recherche :\n--------");
        for (int i = 0; i < liste.size(); i++) {
            System.out.println((i + 1) + ". --");
            liste.get(i).afficherLivre();
            System.out.println("--------");
        }
    }

    /**
     * Affiche dans la console la liste contenu dans l'attribut de la
     * bibliothèque avec une boucle for classique
     */
    public void afficherConsoleAttrListeLivre() {
        System.out.println("--------");
        for (int i = 0; i < listeLivres.size(); i++) {
            System.out.println((i + 1) + ". --");
            listeLivres.get(i).afficherLivre();
            System.out.println("--------");
        }
    }

    /**
     * Affiche dans la console la liste contenu dans l'attribut de la
     * bibliothèque avec une structure foreach (pas forcément plus adaptée ici)
     */
    public void afficherForEachConsoleAttrListeLivre() {
        int i = 1;
        System.out.println("--------");
        for (Livres livre : listeLivres) {
            System.out.println(i + ". --");
            livre.afficherLivre();
            System.out.println("--------");
            i++;
        }
    }

    /**
     * Méthode pour enregistre un livre dans la liste en attribut.
     *
     * @param livre le livre à ajouter
     */
    public void enregistrerLivre(Livres livre) {
        this.listeLivres.add(livre);
    }

    /**
     * Procédure de création de livre avec questionnaire en console.
     *
     * @return le livre créé à partir des infos récoltées au questionnaire.
     */
    public Livres creationLivre() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel est le titre du livre ?");
        String titre = sc.nextLine();
        System.out.println("Quel en est l'auteur ?");
        String auteur = sc.nextLine();
        System.out.println("Quelle est sa référence ?");
        String ref = sc.nextLine();
        System.out.println("Combien de pages ?");
        String nbrePages = sc.nextLine();
        System.out.println("Quel est son édition ?");
        String edition = sc.nextLine();
        System.out.println("De quel genre ?");
        String genre = sc.nextLine();
        System.out.println("Quelle année d'édition ?");
        String anneeEdition = sc.nextLine();
        System.out.println("En quelle langue ?");
        String langue = sc.nextLine();
        System.out.println("Quel est son format ?");
        String format = sc.nextLine();

        Livres livre = new Livres(titre, auteur, ref, nbrePages, edition, genre, anneeEdition, langue, format);
        return livre;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Bibliotheque biblioTest = new Bibliotheque();
        /*
        String titre = "titre";
        String auteur = "auteur";
        String ref = "ref";
        String nbrePages = "nbrePage";
        String edition = "edition";
        String genre = "genre";
        String anneeEdition = "anneeEdition";
        String langue = "langue";
        String format = "format";
        
        
        for (int i = 1; i <= 5; i++) {
            Livre livre = new Livre(titre + i, auteur + i, ref + i, nbrePages + i, edition + i, genre + i, anneeEdition + i, langue + i, format + i);
            biblioTest.listeLivres.add(livre);
        }
                
        //biblioTest.afficherConsoleListeLivre();
        biblioTest.afficherForEachConsoleListeLivre();
         */

        System.out.println(biblioTest.verifContainStr("Bonjour", "bn"));
    }
} 