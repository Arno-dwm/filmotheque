package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bll.GenreService;
import fr.eni.tp.filmotheque.bll.ParticipantService;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.bo.Personne;
import fr.eni.tp.filmotheque.dto.FilmDto;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@SessionAttributes({"realisateurs"})
@Controller
public class FilmController {
    private FilmService filmService;
    private GenreService genreService;
    private ParticipantService participantService;

    public FilmController(FilmService filmService, GenreService genreService, ParticipantService participantService) {

        this.filmService = filmService;
        this.genreService = genreService;
        this.participantService = participantService;
    }


    //méthode accueil
    @GetMapping
    public String afficherFormateurs(Model model){

        return "index";
    }
    //Créer méthodes afficherTousLesfilms
    @GetMapping("films")
    public String afficherFilms(Model model){
        List<Film> films = filmService.consulterFilms();
        for(Film film : films){
            System.out.println(film.toString());
        }
        model.addAttribute("films", films);

        return "view-films";
    }

    @GetMapping("/films/detail")
    public String afficherUnFilm(@RequestParam(name="id") long id, Model model) {
        Film f = filmService.consulterFilmParId(id);
        System.out.println(f.toString());
        model.addAttribute("film", f);

        return "view-detail";
    }
    //méthode pour afficher la vue créer
    @GetMapping("/films/creer")
    public String creerFilm(Model model) {

        if(!model.containsAttribute("filmDto")){
            model.addAttribute("filmDto", new FilmDto());
        }


        return "view-film-form";
    }
/*
    @ModelAttribute("genresEnSession")
    public List<Genre> chargerGenres(){
        System.out.println("Chargement en Session - GENRES");
        return filmService.consulterGenres();
    }
*/
    //@ApplicationScope + retirer de sessionAttribute
    @ModelAttribute("realisateurs")
    public List<Participant> chargerRealisateurs(){
        System.out.println("Chargement des Participants");
        return participantService.consulterParticipants();
    }

    //methode post mapping sur FilmDto
    @PostMapping("/form")
    public String ajouterFilm(@Valid FilmDto filmDto, BindingResult resultat, Model model, RedirectAttributes redirectAttr){


        if(resultat.hasErrors()) {
            //Bonne pratique d'envoi de form : une redirection
            //redirect:/films/creer -> nouvelle requeête qui ne connait pas les attributs de la requete précdédente
            //flashAttribute envoi à la requête suivante (du redirect)
            //Importance des TH:field à la place de name pour que les champs correctement remplis apparaissent même s'il y a eu erreur
            redirectAttr.addFlashAttribute( "org.springframework.validation.BindingResult.filmDto", resultat);
            redirectAttr.addFlashAttribute("filmDto", filmDto);
            //return "accueil";
            return "redirect:/films/creer";
        }

        Film film = new Film();
        BeanUtils.copyProperties(filmDto, film);

        System.out.println("FilmDto genre ID" + filmDto.getIdGenre());


        Genre genre = genreService.findGenreById(filmDto.getIdGenre());
        //Genre genre = new Genre(filmDto.getGenreId());
        System.out.println("Genre ajouté" + genre);
        film.setGenre(genre);


        Participant real = participantService.consulterParticipantParId(filmDto.getIdReal());
        film.setRealisateur(real);

        //Ajouter film à la liste
        filmService.creerFilm(film);


        return "redirect:/films/detail?id=" + film.getId();
    }

}
