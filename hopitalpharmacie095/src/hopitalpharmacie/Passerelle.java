/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hopitalpharmacie;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sio1
 */

public class Passerelle {
    
    /*public static String toStringRS(ResultSet rs) {

        StringBuffer buf = new StringBuffer();
        buf.append("[");
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int nColumns = metaData.getColumnCount();
            for (int i = 1; i <= nColumns; ++i) {
                buf.append(metaData.getColumnName(i));
                buf.append(" = ");
                buf.append(rs.getString(i));
                if (i < nColumns)
                    buf.append(" , ");
            }
        } catch (SQLException e) {
            buf.append(e.getMessage());
            e.printStackTrace();
        }
        buf.append("]");

        return buf.toString();
    }*/
    
    static Connection seConnecter(){
    String url= "jdbc:postgresql://192.168.1.245:5432/hopitalpharmaciemathys";
    String user = "laouadi";
    String passwd = "laouadi";   
    Connection conn;
    
    try{
        conn = DriverManager.getConnection(url, user, passwd);
        //System.out.println("Connexion effective");
        //Statement state=conn.createStatement();
        //return state ;
    }
    catch (SQLException ex){
        // do something appropriate with the exception, *at least*:
        //ex.printStackTrace();
        System.out.println("Erreur");
        conn = null;
    }
    return conn;
    
    }
    
    public static ArrayList<Produit> donnerTousLesProduits(){
        Connection conn = seConnecter();
        Statement state;
        ResultSet jeuResultat;
        ArrayList<Produit> lesProduits = new ArrayList<>();
        try{
            state=conn.createStatement();
        }
        catch(SQLException ex){
            state=null;
        }
        try{
            jeuResultat=state.executeQuery("Select * from \"Produit\"");
        }
        catch(SQLException ex){
            jeuResultat=null;
        }
        try{
            while(jeuResultat.next()){
                int id = jeuResultat.getInt(1);
                String libelle = jeuResultat.getString(2);
                double prix = jeuResultat.getInt(3);
                int quantite = jeuResultat.getInt(4);
                String localisation = jeuResultat.getString(5);
                Produit p1 = new Produit(id, libelle, prix, quantite, localisation );
                lesProduits.add(p1);
            }
        }
        catch(SQLException ex){
            lesProduits=null;
        }    
        return lesProduits;       
    }
    
    public static Produit donnerUnProduit(int id){       
        Connection conn = seConnecter();
        PreparedStatement prepare;
        ResultSet jeuResultat;
        Produit p1=null;
        String requete = "Select * from \"Produit\" where id=?" ;
        try{
            prepare=conn.prepareStatement(requete);
        }
        catch(SQLException ex){
            prepare=null;
        }
        try{
            prepare.setInt(1,id);
            jeuResultat=prepare.executeQuery();
        }
        catch(SQLException ex){
            jeuResultat=null;
        }
        try{
            while(jeuResultat.next()){
                int ID = jeuResultat.getInt(1);
                String libelle = jeuResultat.getString(2);
                double prix = jeuResultat.getInt(3);
                int quantite = jeuResultat.getInt(4);
                String localisation = jeuResultat.getString(5);
                p1 = new Produit(ID, libelle, prix, quantite, localisation );         
            }
        }
        catch(SQLException ex){
            p1=null;
        }    
        return p1;
    }
        
    public static boolean AjouterProduit(Produit unProduit){
        Connection conn = seConnecter();
        PreparedStatement prepare;
        boolean vraiFaux = true;
        String requete = "INSERT INTO \"Produit\" (\"id\",\"libelle\",\"prix\",\"quantite\",\"localisation\") VALUES(?,?,?,?,?)" ;
        try{
            prepare=conn.prepareStatement(requete);
        }
        catch(SQLException ex){
            prepare=null;
            
        }
        try{
            prepare.setInt(1, unProduit.getId());
            prepare.setString(2, unProduit.getLibelle());
            prepare.setDouble(3, unProduit.getPrix());
            prepare.setInt(4, unProduit.getQuantite());
            prepare.setString(5, unProduit.getLocalisation());
        }
        catch(SQLException ex){
            //System.out.println(prepare);
            //vraiFaux = false;
        }
        try{
            prepare.executeUpdate();
        }
        catch(SQLException ex){
            //System.out.println(prepare);
            vraiFaux = false;
        }
        return vraiFaux;    
    }
    
    public static boolean SupprimerProduit(Produit unProduit){
        Connection conn = seConnecter();
        PreparedStatement prepare;
        boolean vraiFaux = true;
        String requete = "DELETE FROM \"Produit\" WHERE id=?" ;
        try{
            prepare=conn.prepareStatement(requete);
        }
        catch(SQLException ex){
            prepare=null;
        }
        try{
            prepare.setInt(1, unProduit.getId());
            prepare.executeUpdate();
        }
        catch(SQLException ex){
            vraiFaux = false;
        }

        return vraiFaux;  
    }
    
    public static boolean ModifierProduit(Produit unProduit){
        Connection conn = seConnecter();
        PreparedStatement prepare;
        boolean vraiFaux = true;
        String requete = "UPDATE \"Produit\" SET libelle=?, prix=?, quantite=?, localisation=? where id=?" ;
        try{
            prepare=conn.prepareStatement(requete);
        }
        catch(SQLException ex){
            prepare=null;
        }
        try{
            prepare.setString(1, unProduit.getLibelle());
            prepare.setDouble(2, unProduit.getPrix());
            prepare.setInt(3, unProduit.getQuantite());
            prepare.setString(4, unProduit.getLocalisation());
            prepare.setInt(5, unProduit.getId());
            prepare.executeUpdate();
        }
        catch(SQLException ex){
            vraiFaux = false;
        }

        return vraiFaux; 
    }
    
    public static boolean SortirProduit(Produit unProduit, int pNbProduits){
        Connection conn = seConnecter();
        PreparedStatement prepare;
        boolean vraiFaux = true;
        String requete = "UPDATE \"Produit\" SET quantite=? WHERE id=?";
        try{
            prepare=conn.prepareStatement(requete);
        }
        catch(SQLException ex){
            prepare=null;
        }
        try{
            prepare.setInt(1, unProduit.getQuantite() - pNbProduits);
            prepare.setInt(2, unProduit.getId());
            prepare.executeUpdate();
        }
        catch(SQLException ex){
            vraiFaux = false;
        }
        return vraiFaux;
    }
    
    public static boolean AjouterSortie(Produit unProduit, int pNbProduits){
        Connection conn = seConnecter();
        PreparedStatement prepare;
        boolean vraiFaux = true;
        String requete = "INSERT INTO \"Sortie\" (\"idUtilisateur\",\"dateSortie\",\"nbProduits\",\"libelleProduit\") VALUES(?,?,?,?)";
        try{
            prepare=conn.prepareStatement(requete);
        }
        catch(SQLException ex){
            prepare=null;
        }
        try{
            prepare.setInt(1, 1);
            prepare.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            prepare.setInt(3, pNbProduits);
            prepare.setString(4, unProduit.getLibelle());
            prepare.executeUpdate();
        }
        catch(SQLException ex){
            vraiFaux = false;
        }
        
        return vraiFaux;
    }
    
    public static boolean VerifierLogin(String pLogin) throws SQLException{
        Connection conn = seConnecter();
        PreparedStatement prepare;
        boolean vraiFaux = true;
        ResultSet jeuResultat ;
        String test = "";
        String requete = "Select \"login\" from \"Connection\" where \"login\" = ?";
        try{
            prepare=conn.prepareStatement(requete);
            prepare.setString(1,pLogin);
            jeuResultat=prepare.executeQuery();
        }
        catch(SQLException ex){
            jeuResultat=null;
        }
        while(jeuResultat.next()){
           test = jeuResultat.getString("login"); 
        }
        if(pLogin == null ? test != null : !pLogin.equals(test)){
            vraiFaux = false;
        }
        return vraiFaux;
    }
    
    public static boolean VerifierMDP(String pMDP, String pLogin) throws SQLException{
        Connection conn = seConnecter();
        PreparedStatement prepare;
        boolean vraiFaux = true;
        ResultSet jeuResultat ;
        String test = "";
        String requete = "Select \"MDP\" from \"Connection\" where \"login\" = ?";
        try{
            prepare=conn.prepareStatement(requete);
            prepare.setString(1,pLogin);
            jeuResultat=prepare.executeQuery();
        }
        catch(SQLException ex){
            jeuResultat=null;
        }
        while(jeuResultat.next()){
           test = jeuResultat.getString("MDP"); 
        }
        if(pMDP == null ? test != null : !pMDP.equals(test)){
            vraiFaux = false;
        }
        return vraiFaux;
    }
    
    public static int getNoUtilisateurParMDP(String pMDP) throws SQLException{
        Connection conn = seConnecter();
        PreparedStatement prepare;
        String requete = "Select \"idUtilisateurs\" from \"Connection\"  where \"MDP\"=?";
        ResultSet jeuResultat ;
        int noUtilisateur=0;
        try{
            prepare=conn.prepareStatement(requete);
            prepare.setString(1,pMDP);
            jeuResultat=prepare.executeQuery();
        }
        catch(SQLException ex){
            jeuResultat=null;
        }
        while(jeuResultat.next()){
           noUtilisateur = jeuResultat.getInt("idUtilisateurs"); 
        }
        return noUtilisateur;
        
    }
    
    public static Utilisateur creerUtilisateur(int numeroUtilisateur) throws SQLException{
        Connection conn = seConnecter();
        PreparedStatement prepare;
        String requete = "Select * from \"Utilisateur\" INNER JOIN \"Connection\" ON \"idUtilisateur\"=\"idUtilisateurs\" where \"idUtilisateurs\"=?";
        ResultSet jeuResultat ;
        int noUtilisateur =0;
        int idMetier=0;
        String nom="";
        try{
            prepare=conn.prepareStatement(requete);
            prepare.setInt(1,numeroUtilisateur);
            jeuResultat=prepare.executeQuery();
        }
        catch(SQLException ex){
            jeuResultat=null;
        }
        while(jeuResultat.next()){
           noUtilisateur = jeuResultat.getInt("noUtilisateur"); 
           idMetier = jeuResultat.getInt("idMetier"); 
           nom = jeuResultat.getString("Nom");
        }
        Utilisateur u1 = new Utilisateur(noUtilisateur, idMetier, nom);
        return u1;
    }
    
    
    public static int getMetier(Utilisateur unUtilisateur) throws SQLException{
        Connection conn = seConnecter();
        PreparedStatement prepare;
        String requete = "Select \"noMetier\" from \"Metier\" INNER JOIN \"Utilisateur\" ON \"noMetier\"=\"idMetier\" where noUtilisateur=?";
        int metier=0;
        ResultSet jeuResultat ;
        try{
            prepare=conn.prepareStatement(requete);
            prepare.setInt(1,unUtilisateur.getNoUtilisateur());
            jeuResultat=prepare.executeQuery();
        }
        catch(SQLException ex){
            jeuResultat=null;
        }
        while(jeuResultat.next()){
           metier= jeuResultat.getInt("noMetier"); 
        } 
        return metier;
    }
    
    public static int getAllSorties(Sortie uneSortie) throws SQLException{
        Connection conn = seConnecter();
        PreparedStatement prepare;
        String requete = "Select \"noMetier\" from \"Metier\" INNER JOIN \"Utilisateur\" ON \"noMetier\"=\"idMetier\" where noUtilisateur=?";
        int metier=0;
        ResultSet jeuResultat ;
        try{
            prepare=conn.prepareStatement(requete);
            prepare.setInt(1,unUtilisateur.getNoUtilisateur());
            jeuResultat=prepare.executeQuery();
        }
        catch(SQLException ex){
            jeuResultat=null;
        }
        while(jeuResultat.next()){
           metier= jeuResultat.getInt("noMetier"); 
        } 
        return metier;
    }
    //A FAIRE !!!!!!   LIste des produits sorties
    
}

