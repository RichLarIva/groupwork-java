package se.iths.grupp1.app.storage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.grupp1.app.model.HorrorMovie;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HorrorMovieStorageTest {
    @TempDir
    Path tempDir;

    @Test
    @DisplayName("Should save and find a horror movie")
    void testShouldSaveAndFindMovie() throws IOException {
        Path filePath = tempDir.resolve("horror.json");
        HorrorMovieStorage storage = new HorrorMovieStorage(filePath);

        HorrorMovie movie = new HorrorMovie(1, "VERY SCARY MOVIE!");
        storage.save(movie);

        HorrorMovie result = storage.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("VERY SCARY MOVIE!", result.getTitle());
    }
}