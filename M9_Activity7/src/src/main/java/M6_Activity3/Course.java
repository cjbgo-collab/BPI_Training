package M6_Activity3;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="course_name", nullable=false)
    private String courseName;

 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="student_id", nullable=false)  
    private Student student;

    public Course() {}

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public Long getId() { return id; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
}