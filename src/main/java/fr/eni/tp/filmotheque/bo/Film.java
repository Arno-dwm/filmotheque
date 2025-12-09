package fr.eni.tp.filmotheque.bo;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Film implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;

    @NotBlank
    private String titre;

    private int annee;
    private int duree;
    private String synopsis;

    private Genre genre;
    private List<Avis> listeAvis=new ArrayList<>();
    private Participant realisateur;
    private List<Participant> listeActeurs=new ArrayList<>();

    public Film(){
        //autre endroit pour initialiser les listes, doit aussi être ajouté aux autres constructeurs
    };

    public Film(int id, String titre, int annee, int duree, String synopsis) {
        this(titre, annee, duree, synopsis); //appel au constructeur avec ces paramètres
        this.id = id;
    }

    public Film(String titre, int annee, int duree, String synopsis) {
        this();
        this.synopsis = synopsis;
        this.duree = duree;
        this.annee = annee;
        this.titre = titre;
    }

    //methods
    @Override
    public String toString() {
        return "Film{" +
                "(" + id + ")" +
                ", Titre : '" + titre + " [" +
                "annee : " + annee +
                ", duree : " + duree + " minutes]" +
                ", Synopsis :'" + synopsis + '\'' +
                ", Genre :" + genre +
                ", Avis : " + listeAvis +
                ", Réalisateur : " + realisateur +
                ", Acteurs : " + listeActeurs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id == film.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Avis> getAvis() {
        return listeAvis;
    }

    public void setListeAvis(List<Avis> listeAvis) {
        this.listeAvis = listeAvis;
    }

    public Participant getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(Participant realisateur) {
        this.realisateur = realisateur;
    }

    public List<Participant> getActeurs() {
        return listeActeurs;
    }

    public void setActeurs(List<Participant> listeActeurs) {
        this.listeActeurs = listeActeurs;
    }
}
