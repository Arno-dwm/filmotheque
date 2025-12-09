package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.exception.GenreNotFound;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GenreRepositoryImpl implements GenreRepository {

    private JdbcTemplate jdbcTemplate;


    public GenreRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> findAllGenres() {
        String sql = "select id, libelle from Genres";
        List<Genre> genres = jdbcTemplate.query(sql,new GenreRowMapper());

        return genres;
    }

    @Override
    public Genre findGenreById(Long id) {
        String sql = "SELECT id, libelle from Genres where id = ?";
        Genre genre = null;

        try{
            genre = jdbcTemplate.queryForObject(sql, new GenreRowMapper(), id);
        } catch (EmptyResultDataAccessException ex){
            throw new GenreNotFound();
        }
        return genre;
    }

    @Override
    public Genre saveGenre(Genre genre) {
        String sql = "INSERT INTO Genres (id, libelle) VALUES (?, ?)";
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, genre.getId());
                ps.setString(2, genre.getTitre());
            } };

        jdbcTemplate.update(sql, pss );

        return genre;

    }


    @Override
    public void updateGenreLibelle(int id, String libelle) {
        String  sql = "UPDATE Genres SET libelle = ? WHERE id = ?";

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, libelle);
                ps.setLong(2, id);
            }
        };

        jdbcTemplate.update(sql, pss);

    }

    @Override
    public void deleteGenre(int id) {
        String sql = "delete from Genres where id = ?";
        jdbcTemplate.update(sql, id);
    }

    class GenreRowMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            Genre genre = new Genre();
            genre.setId(Long.parseLong(String.valueOf(rs.getInt("id"))));
            genre.setTitre(rs.getString("libelle"));

            return genre;
        }
    }
}
