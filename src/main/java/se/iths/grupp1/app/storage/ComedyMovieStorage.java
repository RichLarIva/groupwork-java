package se.iths.grupp1.app.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.grupp1.app.model.ComedyMovie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ComedyMovieStorage implements MovieStorage<ComedyMovie> {

    private final Path filePath;
    private final ObjectMapper mapper;

    public ComedyMovieStorage(Path filePath) {
        this.filePath = filePath;
        this.mapper = new ObjectMapper();
    }

    @Override
    public List<ComedyMovie> findAll() throws IOException {
        if (Files.notExists(filePath)) {
            return new ArrayList<>();
        }
        return mapper.readValue(filePath.toFile(), new TypeReference<List<ComedyMovie>>() {
        });
    }

    @Override
    public ComedyMovie findById(int id) throws IOException {
        List<ComedyMovie> movies = findAll();

        for (ComedyMovie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public void save(ComedyMovie movie) throws IOException {
        if (findById(movie.getId()) != null) {
            return;
        }
        List<ComedyMovie> movies = findAll();
        movies.add(movie);
        mapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), movies);
    }
}

