package se.iths.grupp1.app;

import se.iths.grupp1.app.model.ActionMovie;
import se.iths.grupp1.app.storage.ActionMovieStorage;
import se.iths.grupp1.app.storage.MovieStorage;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    static void main(String[] args) throws IOException {

        MovieStorage<ActionMovie> actionStorage = new ActionMovieStorage(Path.of("data", "action-movies.json"));

        ActionMovie actionMovie1 = new ActionMovie(95016, "Die Hard");
        ActionMovie actionMovie2 = new ActionMovie(133093, "The Matrix");
        ActionMovie actionMovie3 = new ActionMovie(172495, "Gladiator");
        ActionMovie actionMovie4 = new ActionMovie(90605, "Aliens");
        ActionMovie actionMovie5 = new ActionMovie(2402137, "John Wick");

        actionStorage.save(actionMovie1);
        actionStorage.save(actionMovie2);
        actionStorage.save(actionMovie3);
        actionStorage.save(actionMovie4);
        actionStorage.save(actionMovie5);
    }
}
