package M6_Activity4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {

    private static EntityManagerUtil instance;
    private final EntityManagerFactory emf;

    private EntityManagerUtil() {
     
        this.emf = Persistence.createEntityManagerFactory("m6PU");
    }

    public static synchronized EntityManagerUtil getInstance() {
        if (instance == null) {
            instance = new EntityManagerUtil();
        }
        return instance;
    }

    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    public void closeEntityManager(EntityManager em) {
        if (em != null && em.isOpen()) em.close();
    }

    public void shutdownFactory() {
        if (emf != null && emf.isOpen()) emf.close();
    }
}
