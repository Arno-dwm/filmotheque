package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FilmRepositoryImpl implements FilmRepository {

    JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public FilmRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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

    @Override
    public Film findFilmById(long id) {
        String sql = "SELECT Films.id, titre, annee, duree, synopsis, prenom, nom, libelle, genreId, realisateurId  FROM Films INNER JOIN Participants ON Participants.id = Films.realisateurId INNER JOIN Genres ON Genres.id = Films.genreId WHERE Films.id = ?";

        Film film = jdbcTemplate.queryForObject(sql, new FilmRepositoryImpl.FilmRowMapper(), id);

        return film;
    }

    @Override
    public Genre findGenreById(long id) {
        String sql = "SELECT genreId FROM Films WHERE id = ?";
        Genre genre = jdbcTemplate.queryForObject(sql, new GenreRepositoryImpl.GenreRowMapper(), id);

        return genre;
    }

    @Override
    public void creerFilm(Film film) {
        //insert into Films (titre, annee, duree, synopsis, genreId, realisateurID) values ('The BFG', 2016, 117, 'Le Bon Gros Géant est un géant bien différent des autres habitants du Pays des Géants.',5 ,1);
        String sql = "INSERT INTO Films (titre, annee, duree, synopsis, genreId, realisateurID) VALUES (:titre, :annee, :duree, :synopsis, :genreId, :realisateurId)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("titre", film.getTitre());
        parameters.addValue("annee", film.getAnnee());
        parameters.addValue("duree", film.getDuree());
        parameters.addValue("synopsis", film.getSynopsis());
        parameters.addValue("genreId", film.getGenre().getId());
        parameters.addValue("realisateurId", film.getRealisateur().getId());

        namedParameterJdbcTemplate.update(sql, parameters, keyHolder ,new String[] { "id"} );
        film.setId(keyHolder.getKey().intValue());



    }

    class FilmRowMapper implements RowMapper<Film> {

        @Override
        public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
            Film film = new Film();
            Participant realisateur = new Participant(rs.getString(7), rs.getString(6));
            Genre genre = new Genre(rs.getString(8));
            film.setId(rs.getInt("id"));
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
