package se.iths.grupp1.app;

import se.iths.grupp1.app.model.ActionMovie;
import se.iths.grupp1.app.model.ComedyMovie;
import se.iths.grupp1.app.model.DramaMovie;
import se.iths.grupp1.app.model.HorrorMovie;
import se.iths.grupp1.app.storage.*;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    static void main(String[] args) throws IOException {

        MovieStorage<ActionMovie> actionStorage = new ActionMovieStorage(Path.of("data", "action-movies.json"));
        MovieStorage<DramaMovie> dramaStorage = new DramaMovieStorage(Path.of("data", "drama-movies.json"));
        Path horrorMovieFilePath = Path.of("data", "horror-movies.json");
        MovieStorage<HorrorMovie> horrorMovieMovieStorage = new HorrorMovieStorage(horrorMovieFilePath);
        MovieStorage<ComedyMovie> comedyStorage = new ComedyMovieStorage(Path.of("data", "comedy-movies.json"));

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

        DramaMovie dramaMovie1 = new DramaMovie(301, "The Godfather");
        DramaMovie dramaMovie2 = new DramaMovie(302, "The Shawshank Redemption");
        DramaMovie dramaMovie3 = new DramaMovie(303, "Forrest Gump");
        DramaMovie dramaMovie4 = new DramaMovie(304, "The Green Mile");
        DramaMovie dramaMovie5 = new DramaMovie(305, "Schindler`s List");

        dramaStorage.save(dramaMovie1);
        dramaStorage.save(dramaMovie2);
        dramaStorage.save(dramaMovie3);
        dramaStorage.save(dramaMovie4);
        dramaStorage.save(dramaMovie5);

        ComedyMovie comedyMovie1 = new ComedyMovie(401, "Rush Hour");
        ComedyMovie comedyMovie2 = new ComedyMovie(402, "White Chicks");
        ComedyMovie comedyMovie3 = new ComedyMovie(403, "The Hangover");
        ComedyMovie comedyMovie4 = new ComedyMovie(404, "The Other Guys");
        ComedyMovie comedyMovie5 = new ComedyMovie(405, "Superbad");

        comedyStorage.save(comedyMovie1);
        comedyStorage.save(comedyMovie2);
        comedyStorage.save(comedyMovie3);
        comedyStorage.save(comedyMovie4);
        comedyStorage.save(comedyMovie5);

    }
}
