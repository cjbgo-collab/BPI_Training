package M6_Activity5;

import java.util.List;

public class App {

    public static void main(String[] args) {

        StudentJPQLService service = new StudentJPQLService();

        System.out.println("\n--- Student Names ---");
        List<String> names = service.findStudentNames();
        names.forEach(System.out::println);

        System.out.println("\n--- Courses Count for Student #9 ---");
        Long count = service.countCoursesByStudentId(9L);
        System.out.println(count);

        System.out.println("\n--- Students Older Than 20 ---");
        List<Student> students = service.findStudentsByAgeGreaterThan(20);
        students.forEach(s ->
            System.out.println(s.getName() + " " + s.getAge())
        );
    }
}