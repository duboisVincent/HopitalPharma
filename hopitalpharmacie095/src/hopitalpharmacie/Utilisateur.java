/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hopitalpharmacie;

/**
 *
 * @author sio1
 */
public class Utilisateur {

    private int noUtilisateur;
    private int idMetier;
    private String nom;
    
    public Utilisateur(int uNoUtilisateur, int uIdMetier, String uNom) {
        noUtilisateur = uNoUtilisateur;
        idMetier = uIdMetier;
        nom = uNom;// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    /*public Utilisateur(int uNoUtilisateur, int uIdMetier, java.lang.String uNom){
        noUtilisateur = uNoUtilisateur;
        idMetier = uIdMetier;
        nom = uNom;
    }*/
    
    public int getNoUtilisateur(){
        return noUtilisateur ;
    }
    
    public int getIdMetier(){
        return idMetier;
    }
         
    public String getNom(){
        return nom;
    }
    
    public void setNom(String uNom){
        nom = uNom;

    }
    
    public void setIdMetier(int uIdMetier){
        idMetier = uIdMetier;           
    }
    
    public void setNoUtilisateur(int uNoUtilisateur){
        noUtilisateur = uNoUtilisateur;           
    }
    
    
    
}
