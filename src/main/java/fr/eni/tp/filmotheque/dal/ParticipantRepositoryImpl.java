package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.exception.ParticipantNotFound;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ParticipantRepositoryImpl implements ParticipantRepository {

    private JdbcTemplate jdbcTemplate;

    public ParticipantRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Participant> findAllParticipants() {
        String sql = "select id, prenom, nom from participants";
        List<Participant> participants = jdbcTemplate.query(sql,new ParticipantRowMapper());

        return participants;
    }

    @Override
    public Participant findParticipantById(int id) {
        String sql = "select id, prenom, nom from participants where id = ?";
        Participant participant = null;


        try {
            participant = jdbcTemplate.queryForObject(sql, new ParticipantRowMapper(), id);
        } catch(EmptyResultDataAccessException ex) {
            throw new ParticipantNotFound("Participant non trouvé");
        }

        return participant;
    }


    class ParticipantRowMapper implements RowMapper<Participant> {
        @Override
        public Participant mapRow(ResultSet rs, int rowNum) throws SQLException {
            Participant part = new Participant();
            part.setId(rs.getInt("id"));
            part.setPrenom(rs.getString("prenom"));
            part.setNom(rs.getString("nom"));

            return part;
        }
    }
}
