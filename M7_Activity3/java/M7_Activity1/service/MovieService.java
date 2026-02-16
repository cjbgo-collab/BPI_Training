package M7_Activity1.service;

import M7_Activity1.Movie;
import M7_Activity1.repository.MovieRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class MovieService {
    private final EntityManagerFactory emf;

    public MovieService() {
        this.emf = Persistence.createEntityManagerFactory("default");
    }

    public List<Movie> getAll() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            MovieRepository repo = new MovieRepository(em);
            return repo.findAll();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    public Movie create(Movie movie) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = emf.createEntityManager();
            MovieRepository repo = new MovieRepository(em);
            tx = em.getTransaction();
            tx.begin();
            Movie saved = repo.save(movie);
            tx.commit();
            return saved;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }
}