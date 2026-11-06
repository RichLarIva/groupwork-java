package se.iths.grupp1.app.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.grupp1.app.model.ComedyMovie;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComedyMovieStorageTest {

    @TempDir
    Path tempDir;

    private Path testFilePath;

    @BeforeEach
    void setUp() {
        testFilePath = tempDir.resolve("test_comedy_movies.json");
    }

    @Test
    void testSaveAndFindById() throws IOException {
        ComedyMovieStorage storage = new ComedyMovieStorage(testFilePath);
        ComedyMovie testMovie = new ComedyMovie(1, "Rush Hour");

        storage.save(testMovie);
        ComedyMovie foundMovie = storage.findById(1);

        assertNotNull(foundMovie);
        assertEquals(1, foundMovie.getId());
        assertEquals("Rush Hour", foundMovie.getTitle());
    }

    @Test
    void testSaveDuplicate() throws IOException {
        ComedyMovieStorage storage = new ComedyMovieStorage(testFilePath);

        ComedyMovie movie1 = new ComedyMovie(1, "White Chicks");
        ComedyMovie movie2 = new ComedyMovie(1, "The Hangover");

        storage.save(movie1);
        storage.save(movie2);

        List<ComedyMovie> allMovies = storage.findAll();

        assertEquals(1, allMovies.size());

        assertEquals("White Chicks", allMovies.get(0).getTitle());

    }

    @Test
    void testFindAllReturnsEmptyList() throws IOException {
        ComedyMovieStorage storage = new ComedyMovieStorage(testFilePath);

        List<ComedyMovie> allMovies = storage.findAll();

        assertTrue(allMovies.isEmpty());
    }

    @Test
    void testFindByIdMissingId() throws IOException {
        ComedyMovieStorage storage = new ComedyMovieStorage(testFilePath);
        ComedyMovie movie = new ComedyMovie(1, "The Other Guys");

        storage.save(movie);
        ComedyMovie foundMovie = storage.findById(77);

        assertNull(foundMovie);
    }

}
