package M6_Activity5;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String title;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    public Course() {}

    public Course(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getTitle() { return title; }

    // ⭐ ADD THIS so TestDataInserter can update the join-table correctly
    public Set<Student> getStudents() {
        return students;
    }
}