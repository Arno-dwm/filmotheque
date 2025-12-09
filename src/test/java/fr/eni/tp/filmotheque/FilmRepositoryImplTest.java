package fr.eni.tp.filmotheque;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.dal.FilmRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class FilmRepositoryImplTest {
    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("Test findAllfilms all good")
    public void testFindAllFilms() {
        //Arrange : préparation du test, simuler l'état de l'application
        //Act : appel de la méthode à tester
        List<Film> films = filmRepository.findAllFilms();
        System.out.println(films);
        //Assert : vérification du résultat
        assertNotNull(films);
        //à modifier en fonction du nb de films initialisés
        //assertEquals(6, films.size());
    }
}
