package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.dal.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("genreService")
public class GenreServiceImpl implements GenreService{

    private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }


    @Override
    public List<Genre> findAllGenres() {
        return genreRepository.findAllGenres();
    }

    @Override
    public Genre findGenreById(Long id) {
        return genreRepository.findGenreById(id);
    }

    @Override
    public Genre saveGenre(Genre genre) {
        return genreRepository.saveGenre(genre);
    }
}
