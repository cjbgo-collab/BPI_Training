package M6_Activity1;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "StudentA1")
@Table(name = "students")  
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(
        name = "id",
        columnDefinition = "BIGINT" 
    )
    private Long id;

    @Column(
        name = "name",
        columnDefinition = "VARCHAR(100)",
        nullable = false
    )
    private String name;

    @Column(
        name = "age",
        columnDefinition = "INTEGER",
        nullable = false
    )
    private Integer age;

    @Column(
        name = "email",
        columnDefinition = "VARCHAR(100)",
        unique = true,
        nullable = false
    )
    private String email;

 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

