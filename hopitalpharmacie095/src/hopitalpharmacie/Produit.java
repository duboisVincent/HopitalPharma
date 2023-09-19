/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hopitalpharmacie;

/**
 *
 * @author sio1
 */
public class Produit {
    
    private int id;
    private String libelle;
    private double prix;
    private int quantite;
    private String localisation;
    
    //Constructeur
    public Produit(int pId, String pLibelle, double pPrix, int pQuantite, String pLocalisation){
        id = pId;
        libelle = pLibelle;
        prix = pPrix;
        quantite = pQuantite;
        localisation = pLocalisation;            
    }
    
    //Getters
    
    public String getLibelle(){
        return libelle ;
    }
    
    public int getId(){
        return id;
    }
    
    public double getPrix(){
        return prix;
    }
    
    public int getQuantite(){ 
        return quantite;
    }
    
    public String getLocalisation(){ 
        return localisation ;
    }
    
    //Mutateurs
    
    public void setLibelle(String pLibelle){
        libelle = pLibelle;

    }
    
    public void setId(int pId){
        id = pId;           
    }
    
    public void setPrix(double pPrix){
        prix = pPrix;           
    }
    
    public void setQuantite(int pQuantite){
        quantite = pQuantite;           
    }
    
    public void setLocalisation(String pLocalisation){
        localisation = pLocalisation;           
    }
    
    public String toString(){
        String texte = "Ce produit s'appelle '"+libelle+"' il est disponible en "+quantite+" exemplaire(s) au prix unitaire de "+prix+"€, il se trouve "+localisation;
        return texte;
    }
    
    
    
}
