/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pharmacie;

/**
 *
 * @author zking
 */



public class Employe {
    private int id, idService, grade;
    private String nom,prenom,login,passwd;
    
    /*
    *
    *Class Constructor
    *@param id L'identifiant de l'employé
    *@param idService l'identifiant du service dans le quel il travail
    *@param grade le grade de l'employé
    *@param nom Le nom de l'employé
    *@param prenom le prenom de l'employé
    *@param login le login de l'employé
    *@param passwd le mot de passe de l'employé
    */
    public Employe(int id, int idService, int grade, String nom, String prenom, String login, String passwd) {
        this.id = id;
        this.idService = idService;
        this.grade = grade;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.passwd = passwd;
    }
    
    /*
    *Class Method
    *@return l'id de l'employé
    */
    public int getId() {
        return id;
    }
    
    /*
    *Class Method
    *@param id nouveau id de l'employé
    */
    public void setId(int id) {
        this.id = id;
    }

    /*
    *Class Method
    *@return idService l'id du service de l'employé
    */
    public int getIdService() {
        return idService;
    }

    /*
    *Class Method
    *@param idService nouveau id du service de l'employé
    */
    public void setIdService(int idService) {
        this.idService = idService;
    }

    /*
    *Class Method
    *@return grade le grade de l'employé
    */
    public int getGrade() {
        return grade;
    }
    /*
    *Class Method
    *@param grade nouveau grade de l'employé
    */
    public void setGrade(int grade) {
        this.grade = grade;
    }
    
    /*
    *Class Method
    *@return nom le nom de l'employé
    */
    public String getNom() {
        return nom;
    }
    /*
    *Class Method
    *@param nom nouveau nom de l'employé
    */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /*
    *Class Method
    *@return prenom le prenom de l'employé
    */
    public String getPrenom() {
        return prenom;
    }
    
    /*
    *Class Method
    *@param prenom nouveau prenom de l'employé
    */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    /*
    *Class Method
    *@return login le login de l'employé
    */
    public String getLogin() {
        return login;
    }
    
    /*
    *Class Method
    *@param login nouveau login de l'employé
    */
    public void setLogin(String login) {
        this.login = login;
    }
    
    /*
    *Class Method
    *@return passwd le mot de passe de l'employé
    */
    public String getPasswd() {
        return passwd;
    }

    /*
    *Class Method
    *@param passwd nouveau mot de passe de l'employé
    */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    
}
