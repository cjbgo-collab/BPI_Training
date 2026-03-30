package M6_Activity5;

import M6_Activity4.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class StudentJPQLService {

    public List<String> findStudentNames() {
        EntityManager em = EntityManagerUtil.getInstance().createEntityManager();
        List<String> result = em.createQuery(
                "SELECT s.name FROM Student s", String.class
        ).getResultList();
        em.close();
        return result;
    }

    public Long countCoursesByStudentId(Long id) {
        EntityManager em = EntityManagerUtil.getInstance().createEntityManager();
        Long count = em.createQuery(
                "SELECT COUNT(c) FROM Student s JOIN s.courses c WHERE s.id = :id",
                Long.class
        ).setParameter("id", id).getSingleResult();
        em.close();
        return count;
    }

    public List<Student> findStudentsByAgeGreaterThan(int age) {
        EntityManager em = EntityManagerUtil.getInstance().createEntityManager();
        List<Student> result = em.createQuery(
                "SELECT s FROM Student s WHERE s.age > :age",
                Student.class
        ).setParameter("age", age).getResultList();
        em.close();
        return result;
    }
}