package se.iths.grupp1.app.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.grupp1.app.model.DramaMovie;

import javax.swing.border.EmptyBorder;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DramaMovieStorageTest {
    @TempDir
    Path tempDir;

    @Test
    void testSaveAndFindBYId() throws IOException {

        DramaMovie movie = new DramaMovie(1,"Titanic");

        DramaMovieStorage storage = new DramaMovieStorage(tempDir.resolve("drama-test.jason"));
        storage.save(movie);
        DramaMovie savedMovie = storage.findById(1);

        assertEquals (1,savedMovie.getId());
        assertEquals("Titanic", savedMovie.getTitle());

    }
    @Test
    void testDuplicatId() throws IOException {
        DramaMovieStorage storage = new DramaMovieStorage(tempDir.resolve("drama-dublicate-test.json"));

        DramaMovie movie1 = new DramaMovie(1 ,"Titanic");
        DramaMovie movie2 = new DramaMovie(1,"The Shawshank Redemption ");

        storage.save(movie1);
        storage.save(movie2);

        assertEquals(1,storage.findAll().size());
        assertEquals("Titanic",storage.findById(1).getTitle());

    }
    @Test
    void testEmptyStorage() throws  IOException {
        DramaMovieStorage storage =
                new DramaMovieStorage(tempDir.resolve("drama-empty-test.json"));
        assertEquals(0, storage.findAll().size());

    }
    @Test
    void testMissingId() throws IOException {

        DramaMovie movie = new DramaMovie(1, "Titanic");

        DramaMovieStorage storage = new DramaMovieStorage(tempDir.resolve("drama-missing-test.jason"));
        storage.save(movie);

        assertNull(storage.findById(95));


    }
    @Test
    void testMovieOrder() throws IOException {
        DramaMovieStorage storage = new DramaMovieStorage(tempDir.resolve("drama-order-test.json"));

        DramaMovie movie1 = new DramaMovie(1,"Titanic");
        DramaMovie movie2 = new DramaMovie(2, "The Shawshank Redemption");

        storage.save(movie1);
        storage.save(movie2);

        assertEquals("Titanic", storage.findAll().get(0).getTitle());
        assertEquals("The Shawshank Redemption",storage. findAll().get(1).getTitle());






    }


}
