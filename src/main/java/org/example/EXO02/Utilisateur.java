package org.example.EXO02;

public class Utilisateur {
    private String nom;
    private String prenom;
    private String email;
    private int id;
    public Utilisateur(String nom, String prenom, String email){
        this.nom = nom;
        this.prenom = prenom ;
        this.email = email;
    }


    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(int idUtilisateur) {
        this.id = idUtilisateur;
    }
}

