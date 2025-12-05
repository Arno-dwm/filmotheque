package fr.eni.tp.filmotheque.dto;

import jakarta.validation.constraints.NotBlank;

public class FilmDto {
    @NotBlank
    private String titre;

    private int annee;
    private int duree;
    private String synopsis;
    private int idGenre;
    private int idReal;

    public FilmDto(){

    }
    public FilmDto(String titre){
        this.titre = titre;
    }
    public FilmDto(String titre, int annee, int duree, String synopsis, int idGenre) {
        this.titre = titre;
        this.annee = annee;
        this.duree = duree;
        this.synopsis = synopsis;
        this.idGenre = idGenre;
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

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public int getIdReal() {
        return idReal;
    }

    public void setIdReal(int idReal) {
        this.idReal = idReal;
    }

    @Override
    public String toString() {
        return "FilmDto{" +
                "titre='" + titre + '\'' +
                ", annee=" + annee +
                ", duree=" + duree +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }
}
