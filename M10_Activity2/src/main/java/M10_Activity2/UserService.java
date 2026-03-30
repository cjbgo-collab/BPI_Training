package M10_Activity2;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(String username) {
        if (repository.existsById(username)) {
            throw new IllegalArgumentException("Duplicate username");
        }
        return repository.save(new User(username));
    }

    public boolean isDuplicateUsername(String username) {
        return repository.existsById(username);
    }

    public void assignRole(String username, String role) {
        User user = repository.findById(username)
                .orElseThrow(() -> new IllegalArgumentException("User does not exist"));
        user.setRole(role);
        repository.save(user);
    }
}
