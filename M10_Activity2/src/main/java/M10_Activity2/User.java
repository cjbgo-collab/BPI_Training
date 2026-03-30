package M10_Activity2;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;

    private String role;

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public String getUsername() { return username; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }
}