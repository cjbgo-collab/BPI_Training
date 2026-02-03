package M6_Activity4;

import jakarta.persistence.EntityManager;

public class App {

    public static void main(String[] args) {

        EntityManagerUtil emUtil = EntityManagerUtil.getInstance();

     
        EntityManager em1 = emUtil.createEntityManager();
        em1.getTransaction().begin();

        Student newStudent = new Student(20, "student@email.com", "Cathrina");
        em1.persist(newStudent);

        em1.getTransaction().commit();
        emUtil.closeEntityManager(em1); 

        EntityManager em2 = emUtil.createEntityManager();
        System.out.println(
            "is newStudent inside the persistence context: " + em2.contains(newStudent)
        );

       
        em2.getTransaction().begin();
        Student managed = em2.find(Student.class, newStudent.getId());
        managed.setAge(21);
        em2.getTransaction().commit();

        System.out.println(
            "is newStudent inside the persistence context: " + em2.contains(managed)
        );

     
        em2.getTransaction().begin();
        em2.remove(managed);
        em2.getTransaction().commit();
        emUtil.closeEntityManager(em2);

        EntityManager em3 = emUtil.createEntityManager();
        System.out.println(
            "is newStudent inside the persistence context: " + em3.contains(managed)
        );
        emUtil.closeEntityManager(em3);

      
        emUtil.shutdownFactory();
    }
}