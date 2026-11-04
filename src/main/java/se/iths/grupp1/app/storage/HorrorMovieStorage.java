package se.iths.grupp1.app.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.grupp1.app.model.HorrorMovie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class HorrorMovieStorage implements MovieStorage<HorrorMovie> {
    private final Path filePath;
    private final ObjectMapper mapper;

    public HorrorMovieStorage(Path filePath) {
        this.filePath = filePath;
        this.mapper = new ObjectMapper();

        // Should create the folder and resources directory so in case it does not exist
        if (!Files.isDirectory(filePath)) {
            try {
                Files.createDirectories(filePath);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void save(HorrorMovie movie) throws IOException {
        if (findById(movie.getId()) != null) {
            return; // Alright so movie already exists with id then ignore it and DONT SAVE!!!
        }

        List<HorrorMovie> movies = findAll();
        movies.add(movie);

        mapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), movies);
    }

    @Override
    public HorrorMovie findById(int id) throws IOException {
        List<HorrorMovie> movies = findAll();

        for (HorrorMovie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }

        return null;
    }

    @Override
    public List findAll() throws IOException {
        if (Files.notExists(filePath)) {
            return new ArrayList<>();
        }

        return mapper.readValue(filePath.toFile(), new TypeReference<List<HorrorMovie>>() {
        });
    }
}
