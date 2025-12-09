package fr.eni.tp.filmotheque.bo;

import java.io.Serializable;
import java.util.Objects;

public abstract class Personne implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nom;
    private String prenom;

    public Personne(){

    }
    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    public Personne(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }
    //Methods

    @Override
    public String toString() {
        return "{" +
                "(" + id + ")" +
                prenom + " " + nom +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return id == personne.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
