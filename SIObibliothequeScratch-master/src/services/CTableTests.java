package services;

import BDD.CBDD;
import BDD.CParametresStockageBDD;
import entites.CTest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CTableTests {

    protected CBDD bdd;

    public CTableTests(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTest convertir_RS_Test(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            int age = rs.getInt("age");
            return new CTest(id, nom, age);
        } catch (SQLException ex) {
            Logger.getLogger(CTableTests.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int supprimerTable() {
        String req = "DROP TABLE tableTests";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    public int creerTableTests() {
        String requete = "CREATE TABLE IF NOT EXISTS `tableTests` ("
                + "`id` int(11) NOT NULL AUTO_INCREMENT , PRIMARY KEY (`id`), `nom` varchar(50) NOT NULL, `age` int(11) NOT NULL"
                + ") ENGINE = MyISAM DEFAULT CHARSET = latin1;";
        int res = -1;
        System.out.println("" + requete);

        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(requete);
            System.out.println("Resultat requête = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion à la base impossible");
        }
        return res;
    }

    public ArrayList<CTest> lireTests() {

        if (bdd.connecter() == true) {
            ArrayList<CTest> listeTests = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("select * from tableTests");
            try {
                while (rs.next()) {
                    CTest test = convertir_RS_Test(rs);
                    listeTests.add(test);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
            return listeTests;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }

    public ArrayList<CTest> lireTests(String debutNom) {

        if (bdd.connecter() == true) {
            ArrayList<CTest> listeTests = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("select * from tableTests WHERE `nom` LIKE '" + debutNom + "%'");
            try {
                while (rs.next()) {
                    CTest test = convertir_RS_Test(rs);
                    listeTests.add(test);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
            return listeTests;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }

    public CTest lireTest(int id) {
        CTest test = null;
        if (bdd.connecter() == true) {
            System.out.println("Connexion OK");
            ResultSet rs = bdd.executerRequeteQuery("select * from tableTests  WHERE `tableTests`.`id` = " + id);
            try {
                if (rs.next()) {
                    test = convertir_RS_Test(rs);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return test;
    }

    public int insererTest(CTest test) {
        int res = -1;
        if (bdd.connecter() == true) {
            String req = "INSERT INTO `tableTests` (`nom`, `age`)"
                    + "VALUES ('" + CBDD.pretraiterChaineSQL(test.getNom()) 
                    + "', '" + test.getAge() + "');";
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    public int mettreAJourTest(CTest test) {
        int res = -1;
        if (bdd.connecter() == true) {
            String req = "UPDATE `tableTests`"
                    + "SET `nom` = '" + test.getNom() + "', `age` = '" + test.getAge() + "' WHERE `tableTests`.`id` =" + test.getId();
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    public int supprimerTest(int id) {
        int res = -1;
        if (bdd.connecter() == true) {
            String req = "DELETE FROM `tableTests` WHERE `id` = " + id;
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    public int supprimerTousLesTests() {
        int res = -1;
        if (bdd.connecter() == true) {
            String req = "DELETE FROM `tableTests` ";
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    public static void main(String[] args) {
//        CParametresStockageBDD params = new CParametresStockageBDD("parametresBdd.properties");
//        CBDD bdd = new CBDD(params);

//        CBDD bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
        CTableTests tableTests = new CTableTests(new CBDD(new CParametresStockageBDD("parametresBdd.properties")));

        //tableTests.supprimerTable();
        //tableTests.creerTableTests();   // Validé
        //
        tableTests.insererTest(new CTest(-1, "L'oiseau", ((int) (Math.random() * 100))));
//        tableTests.insererTest(new CTest(-1, "BBB", ((int) (Math.random() * 100))));
//        tableTests.insererTest(new CTest(-1, "CCC", ((int) (Math.random() * 100))));
//        tableTests.insererTest(new CTest(-1, "DDD", ((int) (Math.random() * 100))));
//        tableTests.insererTest(new CTest(-1, "EEE", ((int) (Math.random() * 100))));
//        tableTests.mettreAJourTest(new CTest(2, "HuEHuEHuE", 5246));
//        tableTests.supprimerTest(4);
         ArrayList<CTest> listeTests = tableTests.lireTests();
         for (CTest test : listeTests) {
         System.out.println(test);
         }

    }
}
