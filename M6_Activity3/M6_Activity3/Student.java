package M6_Activity3;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="full_name", nullable=false)
    private String fullName;

  
    @OneToMany(mappedBy="student", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Course> courses = new ArrayList<>();

    public Student() {}

    public Student(String fullName) {
        this.fullName = fullName;
    }

   
    public void addCourse(Course course) {
        courses.add(course);
        course.setStudent(this);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        course.setStudent(null);
    }

    public Long getId() { return id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public List<Course> getCourses() { return courses; }
}