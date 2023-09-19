/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hopitalpharmacie;

import static hopitalpharmacie.Passerelle.donnerTousLesProduits;
import static hopitalpharmacie.Passerelle.seConnecter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author sio1
 */
public class Hopitalpharmacie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        //Test fonction donnerTousLesProduits()
        /*Produit p2 = Passerelle.donnerUnProduit(1);
        String retour = p2.toString();
        System.out.println(retour);*/
        
        //Test fonction donnerUnProduit()
        /*Produit p2 = Passerelle.donnerUnProduit(1);
        String retour = p2.toString();
        System.out.println(retour);*/
        
        //Test fonction AjouterProduit()
        /*Produit p1 = new Produit(9,"Doliprane",5.50,80,"Sur l_étagère interdite");
        boolean testAjout = Passerelle.AjouterProduit(p1);
        System.out.println(testAjout);*/
        
        //Test fonction SupprimerProduit()
        /*Produit p3 = Passerelle.donnerUnProduit(52);
        boolean testSuppr = Passerelle.SupprimerProduit(p3);
        System.out.println(testSuppr);*/
        
        //Test fonction ModifierProduit()
        /*Produit p1 = new Produit(2,"Xanax",5.50,80,"Sur l_étagère interdite");
        boolean testModif = Passerelle.ModifierProduit(p1);
        System.out.println(testModif);*/
        
        //Test fonction SortirProduit()
        /*Produit p3 = Passerelle.donnerUnProduit(3);
        boolean testSuppr = Passerelle.SortirProduit(p3, 1);
        System.out.println(testSuppr);*/
        
        //Test fonction AjouterSortie
        /*Produit p3 = Passerelle.donnerUnProduit(3);
        boolean testSuppr = Passerelle.AjouterSortie(p3, 10);
        System.out.println(testSuppr);*/
        
        //Test fonction VerifierLogin
        /*boolean testSuppr = Passerelle.VerifierLogin("MathysLaouadi");
        System.out.println(testSuppr);*/
        
        //Test fonction VerifierLogin
        /*boolean testSuppr = Passerelle.VerifierMDP("testmdp","MathysLaouadi");
        System.out.println(testSuppr);*/
        
        int test = Passerelle.getNoUtilisateurParMDP("CeciEstMonMotDePasse");
        System.out.println(test);
    }
    
}
