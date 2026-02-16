package M7_Activity1.repository;

import M7_Activity1.Movie;
import jakarta.persistence.EntityManager;
import java.util.List;

public class MovieRepository implements Repository<Movie, Long> {
    private final EntityManager em;

    public MovieRepository(EntityManager em) { this.em = em; }

    @Override
    public Movie save(Movie entity) {
        if (entity.getId() == null) em.persist(entity);
        else entity = em.merge(entity);
        return entity;
    }

    @Override
    public void delete(Movie entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    @Override
    public void deleteById(Long id) {
        Movie m = findById(id);
        if (m != null) delete(m);
    }

    @Override
    public Movie findById(Long id) {
        return em.find(Movie.class, id);
    }

    @Override
    public List<Movie> findAll() {
        return em.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
    }
}