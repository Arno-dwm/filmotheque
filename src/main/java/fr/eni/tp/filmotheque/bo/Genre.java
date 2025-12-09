package fr.eni.tp.filmotheque.bo;

import java.io.Serializable;
import java.util.Objects;

public class Genre implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String titre;

    public Genre(){

    }
    public Genre(String titre) {
        this.titre = titre;
    }
    public Genre(int id, String titre) {
        this.titre = titre;
        this.id = id;
    }
    //Methods

    @Override
    public String toString() {
        return "{" +
                titre + "(" + id + ")" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id == genre.id;
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
}
