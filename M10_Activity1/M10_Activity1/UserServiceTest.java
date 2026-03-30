package M10_Activity1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setup() {
        userService = new UserService();
    }

    
    @Test
    void createUser_success() {
        User user = userService.createUser("john");
        assertNotNull(user);
        assertEquals("john", user.getUsername());
    }

     
    @Test
    void createUser_duplicateUsername_fails() {
        userService.createUser("john");

        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> userService.createUser("john")
        );

        assertEquals("Duplicate username", exception.getMessage());
    }

    
    @Test
    void isDuplicateUsername_true() {
        userService.createUser("john");
        assertTrue(userService.isDuplicateUsername("john"));
    }

   
    @Test
    void isDuplicateUsername_false() {
        assertFalse(userService.isDuplicateUsername("mary"));
    }

    
    @Test
    void assignRole_success() {
        userService.createUser("john");
        userService.assignRole("john", "ADMIN");

        User user = userService.getUser("john");
        assertEquals("ADMIN", user.getRole());
    }

   
    @Test
    void assignRole_userNotFound_fails() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> userService.assignRole("ghost", "ADMIN")
        );

        assertEquals("User does not exist", exception.getMessage());
    }
}