package se.iths.grupp1.app.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.grupp1.app.model.ActionMovie;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ActionMovieStorage implements MovieStorage<ActionMovie> {

    private final Path path;
    private final ObjectMapper mapper;

    public ActionMovieStorage(Path path) {
        this.path = path;
        mapper = new ObjectMapper();
    }

    @Override
    public List<ActionMovie> findAll() throws IOException {
        List<ActionMovie> actionMovies = new ArrayList<>();
        if (Files.exists(path)) {
            return mapper.readValue(path.toFile(), new TypeReference<List<ActionMovie>>() {
            });
        }
        return actionMovies;
    }

    @Override
    public ActionMovie findById(int id) throws IOException {
        for (ActionMovie actionMovie : findAll()) {
            if (actionMovie.getId() == id) {
                return actionMovie;
            }
        }
        return null;
    }

    @Override
    public void save(ActionMovie movie) throws IOException {
        if (findById(movie.getId()) == null) {
            List<ActionMovie> actionMovies = findAll();
            actionMovies.add(movie);
            mapper.writeValue(path.toFile(), actionMovies);
        }
    }
}


