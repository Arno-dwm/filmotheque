package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;

import java.util.List;

public interface FilmRepository {
    List<Film> findAllFilms();
    Film findFilmById(long id);
    Genre findGenreById(long id);
    void creerFilm(Film film);
}
