package M7_Activity1.controller;

import M7_Activity1.Movie;
import M7_Activity1.service.MovieService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import static spark.Spark.*;

public class MovieController {
    private final MovieService movieService;
    private final ObjectMapper mapper = new ObjectMapper()
            .findAndRegisterModules()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    public void registerRoutes() {
        after((req, res) -> {
            if (res.type() == null || res.type().isEmpty()) res.type("application/json");
        });

        get("/", (req, res) -> {
            res.type("text/plain");
            return "Movie API is running";
        });

        get("/movies", (req, res) -> {
            var list = movieService.getAll();
            res.status(200);
            return mapper.writeValueAsString(list);
        });

        post("/movies", (req, res) -> {
            Movie incoming = mapper.readValue(req.body(), Movie.class);
            if (incoming == null || isBlank(incoming.getTitle())) {
                res.status(400);
                return "{\"error\":\"Invalid payload: 'title' is required.\"}";
            }
            Movie saved = movieService.create(incoming);
            res.status(201);
            return mapper.writeValueAsString(saved);
        });
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}