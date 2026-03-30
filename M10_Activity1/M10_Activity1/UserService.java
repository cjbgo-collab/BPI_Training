package M10_Activity1;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private Map<String, User> users = new HashMap<>();

    public User createUser(String username) {
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("Duplicate username");
        }

        User user = new User(username);
        users.put(username, user);
        return user;
    }

    public boolean isDuplicateUsername(String username) {
        return users.containsKey(username);
    }

    public void assignRole(String username, String role) {
        User user = users.get(username);
        if (user == null) {
            throw new IllegalArgumentException("User does not exist");
        }
        user.setRole(role);
    }

   
    public User getUser(String username) {
        return users.get(username);
    }
}