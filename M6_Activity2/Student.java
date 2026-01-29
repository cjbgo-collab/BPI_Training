package M6_Activity2;

import jakarta.persistence.*;

@Entity
@Table(name = "students", schema = "public")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // INTEGER to match 'SERIAL' in PostgreSQL

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(name = "age", columnDefinition = "INT")
    private Integer age;

    @Column(name = "email", unique = true, columnDefinition = "VARCHAR(100)")
    private String email;

    // --- Constructors ---
    public Student() {}

    public Student(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // --- Getters & Setters ---
    public Integer getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // --- Optional: handy for logs ---
    @Override
    public String toString() {
        return "Student{id=" + id +
               ", name='" + name + '\'' +
               ", age=" + age +
               ", email='" + email + '\'' +
               '}';
    }
}
