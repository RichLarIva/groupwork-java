package se.iths.grupp1.app;

import se.iths.grupp1.app.model.ActionMovie;
import se.iths.grupp1.app.model.HorrorMovie;
import se.iths.grupp1.app.storage.ActionMovieStorage;
import se.iths.grupp1.app.storage.HorrorMovieStorage;
import se.iths.grupp1.app.storage.MovieStorage;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    static void main(String[] args) throws IOException {

        MovieStorage<ActionMovie> actionStorage = new ActionMovieStorage(Path.of("data", "action-movies.json"));
        Path horrorMovieFilePath = Path.of("data", "horror-movies.json");
        MovieStorage<HorrorMovie> horrorMovieMovieStorage = new HorrorMovieStorage(horrorMovieFilePath);

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

        HorrorMovie horrorMovie0 = new HorrorMovie(231, "In the Mouth of Madness");
        HorrorMovie horrorMovie1 = new HorrorMovie(3123, "The Thing");
        HorrorMovie horrorMovie2 = new HorrorMovie(13125, "The Shining");
        HorrorMovie horrorMovie3 = new HorrorMovie(135345, "Scream");
        HorrorMovie horrorMovie4 = new HorrorMovie(93127, "The Silence of the Lambs");

        horrorMovieMovieStorage.save(horrorMovie0);
        horrorMovieMovieStorage.save(horrorMovie1);
        horrorMovieMovieStorage.save(horrorMovie2);
        horrorMovieMovieStorage.save(horrorMovie3);
        horrorMovieMovieStorage.save(horrorMovie4);
    }
}
