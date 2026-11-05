package se.iths.grupp1.app.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.grupp1.app.model.ActionMovie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ActionMovieStorageTest {

    @TempDir
    Path tempDir;

    Path path;
    ObjectMapper mapper;
    ActionMovieStorage actionMovieStorage;

    @BeforeEach
    void setUp() {
        path = tempDir.resolve("actionMovies.json");
        mapper = new ObjectMapper();
        actionMovieStorage = new ActionMovieStorage(path);
    }

    @Test
    void checkingEmptyListIsReturnedWhenCallingFindAllWhenNoActionMovieIsSavedYet() throws IOException {
        assertTrue(actionMovieStorage.findAll().isEmpty());
    }

    @Test
    void checkingObjectsAreReturnedWhenCallingFindAll() throws IOException {
        ActionMovie actionMovie1 = new ActionMovie(1, "Action1");
        ActionMovie actionMovie2 = new ActionMovie(2, "Action2");
        List<ActionMovie> actionMovies = List.of(actionMovie1, actionMovie2);

        mapper.writeValue(path.toFile(), actionMovies);
        List<ActionMovie> actionMovieList = actionMovieStorage.findAll();

        assertEquals(actionMovie1.getId(), actionMovieList.get(0).getId());
        assertEquals(actionMovie1.getTitle(), actionMovieList.get(0).getTitle());
        assertEquals(actionMovie2.getId(), actionMovieList.get(1).getId());
        assertEquals(actionMovie2.getTitle(), actionMovieList.get(1).getTitle());
        assertEquals(2, actionMovieList.size());
    }

    @Test
    void checkingActionMovieIsReturnedIfExistsWhenCallingFindById() throws IOException {
        ActionMovie actionMovie1 = new ActionMovie(1, "Action1");
        ActionMovie actionMovie2 = new ActionMovie(2, "Action2");
        List<ActionMovie> actionMovies = List.of(actionMovie1, actionMovie2);
        mapper.writeValue(path.toFile(), actionMovies);

        ActionMovie result1 = actionMovieStorage.findById(1);
        ActionMovie result2 = actionMovieStorage.findById(2);

        assertEquals(actionMovie1.getId(), result1.getId());
        assertEquals(actionMovie1.getTitle(), result1.getTitle());
        assertEquals(actionMovie2.getId(), result2.getId());
        assertEquals(actionMovie2.getTitle(), result2.getTitle());
    }

    @Test
    void checkingNullIsReturnedIfIdDoesNotMatchWithAnyActionMovieWhenCallingFindById() throws IOException {
        ActionMovie actionMovie1 = new ActionMovie(1, "Action1");
        ActionMovie actionMovie2 = new ActionMovie(2, "Action2");
        List<ActionMovie> actionMovies = List.of(actionMovie1, actionMovie2);
        mapper.writeValue(path.toFile(), actionMovies);
        assertNull(actionMovieStorage.findById(3));
        assertNull(actionMovieStorage.findById(4));
    }

    @Test
    void checkingActionMovieGetsSavedWhenCallingSaveByIdAndItDoesNotAlreadyExists() throws IOException {
        ActionMovie actionMovie = new ActionMovie(1, "Action1");

        actionMovieStorage.save(actionMovie);

        ActionMovie result = actionMovieStorage.findById(1);

        assertEquals(actionMovie.getId(), result.getId());
        assertEquals(actionMovie.getTitle(), result.getTitle());
    }

    @Test
    void checkingActionMoviesWithSameIdCannotBothBeSaved() throws IOException {
        ActionMovie actionMovie1 = new ActionMovie(1, "Action1");
        ActionMovie actionMovie2 = new ActionMovie(1, "Action2");

        actionMovieStorage.save(actionMovie1);
        actionMovieStorage.save(actionMovie2);

        ActionMovie result = actionMovieStorage.findById(1);

        assertEquals(actionMovie1.getId(), result.getId());
        assertEquals(actionMovie1.getTitle(), result.getTitle());
        assertEquals(1, actionMovieStorage.findAll().size());
    }

    @Test
    void checkingIOExceptionIsThrownWhenJsonIsInvalid() throws IOException {
        Files.writeString(path, "Invalid JSON");
        assertThrows(IOException.class, () -> actionMovieStorage.findAll());
    }
}
