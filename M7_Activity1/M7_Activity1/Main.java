package M7_Activity1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import static spark.Spark.*;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final ObjectMapper mapper = new ObjectMapper()
            .findAndRegisterModules()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    public static void main(String[] args) {
        port(4567);
        after((req, res) -> {
            if (res.type() == null || res.type().isEmpty()) res.type("application/json");
        });
        get("/", (req, res) -> {
            res.type("text/plain");
            return "Movie API is running";
        });
        get("/movies", (req, res) -> {
            EntityManager em = null;
            try {
                em = EntityManagerUtil.getInstance().createEntityManager();
                MovieRepository repo = new MovieRepository(em);
                List<Movie> movies = repo.findAll();
                res.status(200);
                return mapper.writeValueAsString(movies);
            } catch (Exception e) {
                logger.error("Error fetching movies", e);
                res.status(500);
                return toError("Failed to fetch movies.");
            } finally {
                if (em != null && em.isOpen()) em.close();
            }
        });
        post("/movies", (req, res) -> {
            EntityManager em = null;
            EntityTransaction tx = null;
            try {
                em = EntityManagerUtil.getInstance().createEntityManager();
                MovieRepository repo = new MovieRepository(em);
                Movie incoming = mapper.readValue(req.body(), Movie.class);
                if (incoming == null || isBlank(incoming.getTitle())) {
                    res.status(400);
                    return toError("Invalid payload: 'title' is required.");
                }
                tx = em.getTransaction();
                tx.begin();
                Movie saved = repo.save(incoming);
                tx.commit();
                res.status(201);
                return mapper.writeValueAsString(saved);
            } catch (Exception e) {
                if (tx != null && tx.isActive()) tx.rollback();
                logger.error("Error creating movie. Body=" + req.body(), e);
                res.status(500);
                return toError("Failed to create movie.");
            } finally {
                if (em != null && em.isOpen()) em.close();
            }
        });
        exception(Exception.class, (e, req, res) -> {
            logger.error("Unhandled exception", e);
            res.type("application/json");
            res.status(500);
            res.body(toError("Internal server error."));
        });
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static String toError(String msg) {
        return "{\"error\":\"" + msg + "\"}";
    }
}