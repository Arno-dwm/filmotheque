package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FilmRepositoryImpl implements FilmRepository {

    JdbcTemplate jdbcTemplate;

    public FilmRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Film> findAllFilms() {
        //ajouter id genre et id real
        String sql = "SELECT Films.id, titre, annee, duree, synopsis, prenom, nom, libelle  FROM Films\n" +
                "INNER JOIN Participants ON Participants.id = Films.realisateurId\n" +
                "INNER JOIN Genres ON Genres.id = Films.genreId";
        List<Film> films = jdbcTemplate.query(sql,new FilmRepositoryImpl.FilmRowMapper());

        return films;
    }

    class FilmRowMapper implements RowMapper<Film> {

        @Override
        public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
            Film film = new Film();
            Participant realisateur = new Participant(rs.getString(7), rs.getString(6));
            Genre genre = new Genre(rs.getString(8));
            film.setId(rs.getLong("id"));
            film.setTitre(rs.getString("titre"));
            film.setAnnee(rs.getInt("annee"));
            film.setDuree(rs.getInt("duree"));
            film.setSynopsis(rs.getString("synopsis"));
            film.setRealisateur(realisateur);
            film.setGenre(genre);

            return film;
        }
    }
}
