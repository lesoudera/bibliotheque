/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotheque;

import BDD.CBDD;
import BDD.CParametresStockageBDD;
import IHM.JDialogModifier;
import IHM.JFrameBibliotheque;
import entites.Livre;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import services.CTableLivres;

/**
 *
 * @author gimli
 */
public class App {

    public Bibliotheque biblio;
    public JFrameBibliotheque jFrameBiblio;
    public CTableLivres tableLivres;
    public JDialogModifier jDialogModifier;

    public void runBibliothequeIHM() {

        biblio = new Bibliotheque();
        jFrameBiblio = new JFrameBibliotheque();
        jFrameBiblio.app = this;
        tableLivres = new CTableLivres(new CBDD(new CParametresStockageBDD("parametresBdd.properties")));
        this.majBiblio();
        //this.afficherListejTableBiblio();

        //this.setRowCountjTableBiblio(0);
        this.jFrameBiblio.getjTableBibliotheque().setRowSelectionInterval(0, 0);
        jFrameBiblio.setVisible(true);

    }

    public void majBiblio() {
        biblio.listeLivres = tableLivres.lireLivres();
        this.afficherListejTableBiblio();
    }

    public void setRowCountjTableBiblio(int rowCount) {
        DefaultTableModel model = (DefaultTableModel) jFrameBiblio.getjTableBibliotheque().getModel();
        model.setRowCount(rowCount);
        jFrameBiblio.getjTableBibliotheque().setModel(model);
        this.jFrameBiblio.idLivres = new String[rowCount];
    }

    public void afficherListejTableBiblio() {
        this.setRowCountjTableBiblio(this.biblio.listeLivres.size());

        for (int i = 0; i < this.biblio.listeLivres.size(); i++) {
            this.jFrameBiblio.idLivres[i] = this.biblio.listeLivres.get(i).getId();
            this.jFrameBiblio.getjTableBibliotheque().setValueAt(this.biblio.listeLivres.get(i).getTitre(), i, 0);
            this.jFrameBiblio.getjTableBibliotheque().setValueAt(this.biblio.listeLivres.get(i).getAuteur(), i, 1);
            this.jFrameBiblio.getjTableBibliotheque().setValueAt(this.biblio.listeLivres.get(i).getRef(), i, 2);
            this.jFrameBiblio.getjTableBibliotheque().setValueAt(this.biblio.listeLivres.get(i).getNbrePages(), i, 3);
            this.jFrameBiblio.getjTableBibliotheque().setValueAt(this.biblio.listeLivres.get(i).getEdition(), i, 4);
            this.jFrameBiblio.getjTableBibliotheque().setValueAt(this.biblio.listeLivres.get(i).getGenre(), i, 5);
            this.jFrameBiblio.getjTableBibliotheque().setValueAt(this.biblio.listeLivres.get(i).getAnneeEdition(), i, 6);
            this.jFrameBiblio.getjTableBibliotheque().setValueAt(this.biblio.listeLivres.get(i).getLangue(), i, 7);
            this.jFrameBiblio.getjTableBibliotheque().setValueAt(this.biblio.listeLivres.get(i).getFormat(), i, 8);
        }
        System.out.println("Point d'arrêt");
    }

    public void afficherListejTableBiblio(ArrayList<Livre> list) {
        this.setRowCountjTableBiblio(list.size());

        for (int i = 0; i < list.size(); i++) {
            this.jFrameBiblio.idLivres[i] = list.get(i).getId();
            this.jFrameBiblio.getjTableBibliotheque().setValueAt(list.get(i).getTitre(), i, 0);
            this.jFrameBiblio.getjTableBibliotheque().setValueAt(list.get(i).getAuteur(), i, 1);
            this.jFrameBiblio.getjTableBibliotheque().setValueAt(list.get(i).getRef(), i, 2);
            this.jFrameBiblio.getjTableBibliotheque().setValueAt(list.get(i).getNbrePages(), i, 3);
            this.jFrameBiblio.getjTableBibliotheque().setValueAt(list.get(i).getEdition(), i, 4);
            this.jFrameBiblio.getjTableBibliotheque().setValueAt(list.get(i).getGenre(), i, 5);
            this.jFrameBiblio.getjTableBibliotheque().setValueAt(list.get(i).getAnneeEdition(), i, 6);
            this.jFrameBiblio.getjTableBibliotheque().setValueAt(list.get(i).getLangue(), i, 7);
            this.jFrameBiblio.getjTableBibliotheque().setValueAt(list.get(i).getFormat(), i, 8);
        }
    }

    public void ajouterLivreIHM() {
        //this.jFrameBiblio.getjTableSelection().setValueAt(this.jFrameBiblio.getjTableSelection().getValueAt(0, 0), 0, 1);
        //String str = this.jFrameBiblio.getjTableSelection().getValueAt(0, 0).toString();
        //System.out.println(str);

        String titre = this.jFrameBiblio.getjTableSelection().getValueAt(0, 0).toString();
        String auteur = this.jFrameBiblio.getjTableSelection().getValueAt(0, 1).toString();
        String ref = this.jFrameBiblio.getjTableSelection().getValueAt(0, 2).toString();
        String nbrePages = this.jFrameBiblio.getjTableSelection().getValueAt(0, 3).toString();
        String edition = this.jFrameBiblio.getjTableSelection().getValueAt(0, 4).toString();
        String genre = this.jFrameBiblio.getjTableSelection().getValueAt(0, 5).toString();
        String anneeEdition = this.jFrameBiblio.getjTableSelection().getValueAt(0, 6).toString();
        String langue = this.jFrameBiblio.getjTableSelection().getValueAt(0, 7).toString();
        String format = this.jFrameBiblio.getjTableSelection().getValueAt(0, 8).toString();

        Livre livre = this.biblio.creationLivre(titre, auteur, ref, nbrePages, edition, genre, anneeEdition, langue, format);
        this.tableLivres.insererLivre(livre);
        this.majBiblio();
        System.out.println("Point d'arrêt");
        //this.afficherListejTableBiblio();
    }

    public void searchIHM(String text, int selectedIndex) {
        if (selectedIndex == 0) {
            this.afficherListejTableBiblio(this.biblio.globaSearch(text));
        } else {
            this.afficherListejTableBiblio(this.biblio.uniSearch(text, selectedIndex));
        }

    }

    public void supprimerLivreIHM(String idLivre) {
        //this.tableLivres.supprimerLivre(new Livre(idLivre));
        this.tableLivres.supprimerLivre(idLivre);

        this.majBiblio();
    }

    public void modifierLivreIHM(String idLivre, String titre, String auteur, String ref,
            String pages, String edition, String genre, String annee, String langue, String format) {

        jDialogModifier = new JDialogModifier(this.jFrameBiblio, true);

        this.jDialogModifier.idLivre = idLivre;
        this.jDialogModifier.getjTableSelection().setValueAt(titre, 0, 0);
        this.jDialogModifier.getjTableSelection().setValueAt(auteur, 0, 1);
        this.jDialogModifier.getjTableSelection().setValueAt(ref, 0, 2);
        this.jDialogModifier.getjTableSelection().setValueAt(pages, 0, 3);
        this.jDialogModifier.getjTableSelection().setValueAt(edition, 0, 4);
        this.jDialogModifier.getjTableSelection().setValueAt(genre, 0, 5);
        this.jDialogModifier.getjTableSelection().setValueAt(annee, 0, 6);
        this.jDialogModifier.getjTableSelection().setValueAt(langue, 0, 7);
        this.jDialogModifier.getjTableSelection().setValueAt(format, 0, 8);

        jDialogModifier.setVisible(true);
    }

}
