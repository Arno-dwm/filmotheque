package fr.eni.tp.filmotheque;

import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.dal.GenreRepository;
import fr.eni.tp.filmotheque.exception.GenreNotFound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FilmothequeApplicationTests {

    @Autowired
    private GenreRepository genreRepository;


	@Test
	void contextLoads() {
	}

    @Test
    @DisplayName("Test findAllGenres all good")
    void testFindAllGenres() {
        //Arrange : préparation du test, simuler l'état de l'application
        //Act : appel de la méthode à tester
        List<Genre> genres = genreRepository.findAllGenres();

        //Assert : vérification du résultat
        assertNotNull(genres);
        assertEquals(6, genres.size());
    }

    @Test
    @DisplayName("Vérifie qu'un genre est retourné quand on fournit un id connu (Cas positif)")
    void testFindGenreByIdCasIdExistant() {
        Genre genre=null;
        try {
            genre = genreRepository.findGenreById(1L);
        } catch (GenreNotFound ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        System.out.println(genre);
    }

    @Test
    @DisplayName("Vérifie qu'une erreur est retournée quand on fournit un id inconnu (Cas négatif)")
    void testFindPizzaByIdCasIdInconnu() {
        Genre genre=null;
        try {
            genre = genreRepository.findGenreById(99L);

            //Fail - Echec du test
            fail();
        } catch (GenreNotFound ex) {
            //Test réussi car ID est bien inexistant

        }
    }
}
