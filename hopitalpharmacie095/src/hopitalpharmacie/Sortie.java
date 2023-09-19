/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hopitalpharmacie;

import java.time.LocalDate;

/**
 *
 * @author sio1
 */
public class Sortie {
    private int idSortie;
    private String utilisateur;
    private LocalDate dateSortie;
    private int nbProduits;
    private String libelleProduit;
    
    //Constructeur
    public Sortie(int pIdSortie, String pUtilisateur, LocalDate pDateSortie, int pNbProduits, String pLibelleProduit){
        idSortie = pIdSortie;
        utilisateur = pUtilisateur;
        dateSortie = pDateSortie;
        nbProduits = pNbProduits;
        libelleProduit = pLibelleProduit;            
    }
    
    //Getters
    
    public String getUtilisateur(){
        return utilisateur ;
    }
    
    public int getIdSortie(){
        return idSortie;
    }
    
    public LocalDate getDateSortie(){
        return dateSortie;
    }
    
    public int getNbProduits(){ 
        return nbProduits;
    }
    
    public String getLibelleProduit(){ 
        return libelleProduit ;
    }
    
    //Mutateurs
    
    public void setUtilisateur(String pUtilisateur){
        utilisateur = pUtilisateur;

    }
    
    public void setIdSortie(int pIdSortie){
        idSortie = pIdSortie;           
    }
    
    public void setDateSortie(LocalDate pDateSortie){
        dateSortie = pDateSortie;           
    }
    
    public void setNbProduits(int pNbProduits){
        nbProduits = pNbProduits;           
    }
    
    public void setLibelleProduit(String pLibelleProduit){
        libelleProduit = pLibelleProduit;           
    }
    
    @Override
    public String toString(){
        String texte = "Ce produit s'appelle '"+libelleProduit+"' il y a "+nbProduits+" exemplaire(s) qui ont été sortie(s) le "+dateSortie+" par "+utilisateur+" l'ID de cette sortie est: "+idSortie;
        return texte;
    }
    
}
