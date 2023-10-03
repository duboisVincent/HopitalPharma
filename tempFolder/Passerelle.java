/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pharmacie;

import java.util.ArrayList;
import java.sql.*;
import java.security.*;

/**
 *
 * @author zking
 */
public class Passerelle {

    private static Connection conn;
    private static int idEmploye, idGrade;

    /*
    *Class Method
    *@return conn Object connection
    *
    *Permet de se connecter à la base de donnée
    */
    public static void connexion() {
        try {
            String url = "jdbc:postgresql://192.168.1.245:5432/Slam2024HopitalPharmacie_Dubois"; // à changer en cours
            String user = "dubois";
            String passwd = "Chienchatcheval.";
            conn = DriverManager.getConnection(url, user, passwd);

            System.out.println("connexion réussie");

        } catch (SQLException e) {
            System.out.println("Echec de la connexion : " + e);
        }
    }

    /*
    *Class Method
    *
    *Ferme la conenection à la base de donnée
    */
    public static void deconnexion() {
        try {
            conn.close();
            System.out.println("Connexion fermée");
        } catch (SQLException e) {
            System.out.println("ERROR (deconnection BDD) : " + e);
        }
    }
    
    /*
    *Class Method
    *@return ArraysList<Produit>
    *
    *Renvoie une liste de tout les produits de la base de donnée, 
    *sous la forme d'objet produits, de forme (id,nom,prix,qtte,local)
    */
    public static ArrayList<Produit> donnerTousLesProduits() {
        ArrayList<Produit> lesProduits = new ArrayList<>();
        try {
            Produit p;
            java.sql.Statement state = conn.createStatement();
            ResultSet R1 = state.executeQuery("select * from \"medicament\"");
            while (R1.next()) {
                int id = R1.getInt(1);
                String nom = R1.getString(2);
                int prix = R1.getInt(4);
                int qtte = R1.getInt(5);
                String local = R1.getString(3);
                p = new Produit(id, nom, prix, qtte, local);
                lesProduits.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Echec de la connexion : " + e);
        }
        return lesProduits;

    }
    
    /*
    *Class Method
    *@param unId identifiant du produit à trouver
    *@return un Objet produit sous la forme (id,nom,prix,qtte,local)
    */
    public static Produit donnerUnProduit(int unId) {
        Produit p = null;
        try {
            PreparedStatement sql = conn.prepareStatement("select * from \"medicament\" where id=?");
            sql.setInt(1, unId);
            ResultSet R2 = sql.executeQuery();
            if (R2.next()) {
                int id = R2.getInt(1);
                String nom = R2.getString(2);
                int prix = R2.getInt(3);
                int qtte = R2.getInt(4);
                String local = R2.getString(5);
                p = new Produit(id, nom, prix, qtte, local);
            }
        } catch (SQLException e) {
            System.out.println("echec : " + e);
        }
        return (p);
    }
    
    /*
    *Class Method
    *@param unProduit, un objet prduit à enregister en base de donnée
    *@return true, obligatoire pour le bon fonctionnement de la fonction
    *Ajoute un produit à la base de donnée
    */
    public static boolean ajouterProduit(Produit unProduit) {
        try {
            PreparedStatement sql = conn.prepareStatement("insert into \"medicament\" (libelle,prix,quantite,localisation) values (?,?,?,?)");
            sql.setString(1, unProduit.getLibelle());
            sql.setInt(2, unProduit.getPrix());
            sql.setInt(3, unProduit.getQuantite());
            sql.setString(4, unProduit.getLocalisation());
            sql.executeUpdate();
        } catch (SQLException e) {
            System.out.println("erreur : " + e);
        }
        return (true);
    }

    /*
    *Class Method
    *@param unProduit, un objet prduit à modifier en base de donnée
    *@return true si la modification s'est faite
    *@return false si la modification ne s'est pas faite
    *Modifie un produit de Donnée
    */    
    public static boolean modifierProduit(Produit unProduit) {
        int i = 0;
        boolean retour = false;
        try {
            PreparedStatement sql = conn.prepareStatement("UPDATE \"medicament\" SET libelle=?,prix=?,quantite=?,localisation=? where id=?;");
            sql.setString(1, unProduit.getLibelle());
            sql.setInt(2, unProduit.getPrix());
            sql.setInt(3, unProduit.getQuantite());
            sql.setString(4, unProduit.getLocalisation());
            sql.setInt(5, unProduit.getId());
            i = sql.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur : " + e);
        }
        if (i > 0) {
            retour = true;
        }
        return (retour);
    }

    /*
    *Class Method
    *@param unProduit, un objet prduit à supprimer de la base de donnée
    *@return true, si la suppresion s'est faite
    *@return false si la suppression ne s'est pas faite
    *
    *Supprime un produit de la base de donnée
    */    
    public static boolean supprimerProduit(Produit unProduit) {
        int i = 0;
        boolean retour = false;
        try {
            PreparedStatement sql = conn.prepareStatement("DELETE FROM \"medicament\" WHERE id =?;");
            sql.setInt(1, unProduit.getId());
            i = sql.executeUpdate();
        } catch (SQLException e) {
            System.out.println("echec : " + e);
        }
        if (i > 0) {
            retour = true;
        }
        return (retour);
    }

    /*
    *Class Method
    *@param pLogin login de l'utilisateur
    *@param pMdp mot de passe de l'utilisateur
    *@throws NoSuchAlgorithmException indispensable pour le fonctionnement de 
    *   MessageDigest et de StringBuilder
    *@return true, si le mot de passe associé à l'identifaint est le même que
    *   celui donnée en paramètre
    *@return false si c'est n'est pas le cas
    *
    * Verifie les informations de connection d'un utilisateur
    */        
    public static boolean authentification(String pLogin, String pMdp) throws NoSuchAlgorithmException {
        int i = 0;
        String mdp = null;
        try {
            PreparedStatement sql = conn.prepareStatement("select motdepasse, id,grade from employe where login=?");
            sql.setString(1, pLogin);
            ResultSet R2 = sql.executeQuery();
            if (R2.next()) {
                mdp = R2.getString(1);
                idEmploye = R2.getInt(2);
                idGrade = R2.getInt(3);
            }
            String input = pMdp;
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(input.getBytes());

            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            if (mdp.equals(sb.toString())) {
                i = 1;
            }

        } catch (SQLException e) {

        }
        return (i > 0);
    }

    /*
    *Class Method
    *@param idEmploye identifiant de l'employé
    *@return true si le log à été mis à jour
    *       false dans le cas contraire
    *garde en base de donnée l'historique des modifications
    */    
    public static boolean log(int idEmploye) {
        int i = 0;
        try {
            PreparedStatement sql = conn.prepareStatement("insert into authentification(date,\"idemploye\") values(?,?) ");
            Date dateAInserer = new Date(System.currentTimeMillis()); // Utilisez la date actuelle ici
            sql.setDate(1, dateAInserer);
            sql.setInt(2, idEmploye);
            i = sql.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return (i > 0);
    }

    /*
    *Class Method
    *@param idMedicament identifiant du médicatent
    *@param quantite quantite prise du stock
    *@param idEmploye identifiant de l'employe qui à fait la sortie
    *@Throws SQLException, permet de renvoyer un message d'erreur dans un
    *       try and catch
    *@return true si la sortie à été faite
    *@return false dans le cas contraire
    */    
    public static boolean sortie(int idMedicament, int quantite, int idEmploye) throws SQLException {
        int i = 0;
        int j=0;
        try {
            //creation de la ligne de sortie dans la table Sortie
            PreparedStatement insert = conn.prepareStatement("insert into sortie(date,idemploye,idmedicament,quantite) values(?,?,?,?)");
            Date dateAInserer = new Date(System.currentTimeMillis()); // Utilisez la date actuelle ici
            insert.setDate(1, dateAInserer);
            insert.setInt(2, idEmploye);
            insert.setInt(3, idMedicament);
            insert.setInt(4, quantite);
            //update de la table medicament afin de changer la quantite
            PreparedStatement update=conn.prepareStatement("update medicament set quantite =quantite-? where id=?");
            update.setInt(1, quantite);
            update.setInt(2, idMedicament);
            
            j=update.executeUpdate();
            i=insert.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
        return(i>0 && j>0);
    }
}
