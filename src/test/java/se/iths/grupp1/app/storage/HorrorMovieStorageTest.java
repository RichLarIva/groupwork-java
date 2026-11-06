package se.iths.grupp1.app.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.grupp1.app.model.HorrorMovie;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HorrorMovieStorageTest {
    @TempDir
    Path tempDir;

    Path filePath;
    HorrorMovieStorage storage;

    @BeforeEach
    void setUp()
    {
        filePath = tempDir.resolve("horror.json");
        storage = new HorrorMovieStorage(filePath);
    }

    @Test
    @DisplayName("Should save and find a horror movie")
    void testShouldSaveAndFindMovie() throws IOException {

        HorrorMovie movie = new HorrorMovie(1, "VERY SCARY MOVIE!");
        storage.save(movie);

        HorrorMovie result = storage.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("VERY SCARY MOVIE!", result.getTitle());
    }

    @Test
    @DisplayName("Should return empty file")
    void testShouldReturnEmptyList() throws IOException
    {
        assertTrue(storage.findAll().isEmpty());
    }

    @Test
    @DisplayName("Should not save movies with duplicate IDs")
    void testShouldNotSaveMoviesWithDuplicateIDs() throws IOException
    {
        HorrorMovie firstMovie = new HorrorMovie(1, "VERY SCARY MOVIE!1!");
        HorrorMovie secondMovie = new HorrorMovie(1, "potatoes in space");

        storage.save(firstMovie);
        storage.save(secondMovie);

        List<HorrorMovie> movies = storage.findAll();

        assertEquals(1, movies.size());
        assertEquals("VERY SCARY MOVIE!1!", movies.getFirst().getTitle());
        assertEquals(1, movies.getFirst().getId());
    }

    @Test
    @DisplayName("Should return null when movie ID does not exist")
    void testShouldReturnNullWhenMovieIDDoesNotExist() throws IOException
    {
        HorrorMovie movie = new HorrorMovie(1, "Potatoes vs Mashed Potatoes");

        storage.save(movie);

        HorrorMovie result = storage.findById(2);

        assertNull(result);
    }
}