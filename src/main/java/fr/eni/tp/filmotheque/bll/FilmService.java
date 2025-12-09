package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;

import java.util.List;

public interface FilmService {
    List<Film> consulterFilms();
    Film consulterFilmParId(long id);

    //Remplacée par les methodes dans GenreService
  //  List<Genre> consulterGenres();
    //Genre consulterGenreParId(long id);

    //A remplacer par des methodes ParticipantService
   // List<Participant> consulterParticipants();
   // Participant consulterParticipantParId(int id);
    void creerFilm(Film film);
}
