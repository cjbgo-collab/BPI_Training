package M6_Activity3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sandboxPU");

        Long studentId = null;

    
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Student student = new Student("Cathrina Go");
            student.addCourse(new Course("Java Programming"));
            student.addCourse(new Course("Database Systems"));

            em.persist(student);

            em.getTransaction().commit();

            studentId = student.getId();  
            System.out.println("✅ Insert committed. Student ID = " + studentId);

        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close(); 
        }

      
        EntityManager em2 = emf.createEntityManager();
        try {
           
            Student savedStudent = em2.createQuery(
                    "SELECT s FROM Student s " +
                    "JOIN FETCH s.courses " +
                    "WHERE s.id = :id", Student.class)
                .setParameter("id", studentId)
                .getSingleResult();


            savedStudent.getCourses().forEach(c ->
                System.out.println(" - " + c.getCourseName() + " (Course ID: " + c.getId() + ")")
            );

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em2.close();
            emf.close();
        }
    }
}
