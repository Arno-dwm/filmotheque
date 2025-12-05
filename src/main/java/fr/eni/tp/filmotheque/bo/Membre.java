package fr.eni.tp.filmotheque.bo;

import java.util.Objects;

public class Membre extends Personne {
    private static final long serialVersionUID = 1L;
    private String pseudo;
    private String motDePasse;
    private boolean admin=false;

    public Membre() {

    }
    public Membre(String nom, String prenom, String pseudo, String motDePasse) {
        super(nom, prenom);
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;

    }
    public Membre(long id, String nom, String prenom, String pseudo, String motDePasse) {
        super(id, nom, prenom);
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;

    }

    //Methods

    @Override
    public String toString() {
        return "Membre{" +
                "admin=" + admin +
                ", pseudo='" + pseudo + '\'' +
                '}' + super.toString();
    }




    //Getters and setters
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
