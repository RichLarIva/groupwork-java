package se.iths.grupp1.app;

import se.iths.grupp1.app.model.*;
import se.iths.grupp1.app.storage.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Main {
    private static final String MENU = """
            1. Visa filmer i en viss kategori
            2. Visa all filmer
            0. Avsluta
            """;

    static void main(String[] args) throws IOException {

        List<MovieStorage> storages = new ArrayList<>();
        MovieStorage<ActionMovie> actionStorage = new ActionMovieStorage(Path.of("data", "action-movies.json"));
        MovieStorage<DramaMovie> dramaStorage = new DramaMovieStorage(Path.of("data", "drama-movies.json"));
        Path horrorMovieFilePath = Path.of("data", "horror-movies.json");
        MovieStorage<HorrorMovie> horrorMovieMovieStorage = new HorrorMovieStorage(horrorMovieFilePath);
        MovieStorage<ComedyMovie> comedyStorage = new ComedyMovieStorage(Path.of("data", "comedy-movies.json"));

        storages.add(horrorMovieMovieStorage);
        storages.add(actionStorage);
        storages.add(dramaStorage);
        storages.add(comedyStorage);

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

        boolean running = true;

        while (running) {
            IO.println(MENU);
            String input = IO.readln().trim().toLowerCase();

            switch (input) {
                case "1":
                    showCategoryMovies(storages);
                    break;
                case "2":
                    showAllMovies(storages);
                    break;
                case "0":
                case "q":
                    IO.println("Quitting application!");
                    running = false;
                    break;
                default:
                    IO.println("Invalid option. Please try again.\n");
            }
        }

    }

    private static void showAllMovies(List<MovieStorage> storages) {
        List<Movie> allMovies = new ArrayList<>();

        for (int i = 0; i < storages.size(); i++) {
            try {
                allMovies.addAll(storages.get(i).findAll());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        displayMovies("All Movies", allMovies);
    }

    private static void showCategoryMovies(List<MovieStorage> storages) {
        IO.println("Select a category:");
        for (int i = 0; i < storages.size(); i++) {
            if (storages.get(i) instanceof HorrorMovieStorage) {
                IO.println(i + 1 + " Horror movies");
            }
            else if (storages.get(i) instanceof ComedyMovieStorage) {
                IO.println(i + 1 + " Comedy movies");
            }
            else if (storages.get(i) instanceof ActionMovieStorage) {
                IO.println(i + 1 + " Action movies");
            }
            else if (storages.get(i) instanceof DramaMovieStorage) {
                IO.println(i + 1 + " Drama movies");
            }
        }

        try {


            int choice = Integer.parseInt(IO.readln("Choice: "));

            switch (choice - 1) {
                case 0:
                    displayMovies("Horror", storages.get(0).findAll());
                    break;
                case 1:
                    displayMovies("Action", storages.get(1).findAll());
                    break;
                case 2:
                    displayMovies("Drama", storages.get(2).findAll());
                    break;
                case 3:
                    displayMovies("Comedy", storages.get(3).findAll());
                    break;
            }
        }
        catch (NumberFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void displayMovies(String header, List<Movie> movies) {
        IO.println("--- " + header + " ---");
        int count = 0;

        for (var movie : movies) {
            IO.println(movie.getTitle() + " \n\t" + movie.getId() + " \n\t" + movie.category() + "\n");
        }
    }
}
