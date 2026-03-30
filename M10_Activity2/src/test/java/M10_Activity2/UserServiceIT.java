package M10_Activity2;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repository;

    @BeforeEach
    void clearDB() {
        repository.deleteAll();
    }

    @Test
    void test1_createUser_success() {
        User u = userService.createUser("john");
        assertEquals("john", u.getUsername());
        assertTrue(repository.existsById("john"));
    }

    @Test
    void test2_createUser_duplicateUsername_fails() {
        userService.createUser("john");
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> userService.createUser("john"));
        assertEquals("Duplicate username", ex.getMessage());
    }

    @Test
    void test3_isDuplicateUsername_true() {
        userService.createUser("john");
        assertTrue(userService.isDuplicateUsername("john"));
    }

    @Test
    void test4_isDuplicateUsername_false() {
        assertFalse(userService.isDuplicateUsername("mary"));
    }

    @Test
    void test5_assignRole_success() {
        userService.createUser("john");
        userService.assignRole("john", "ADMIN");
        assertEquals("ADMIN", repository.findById("john").get().getRole());
    }

    @Test
    void test6_assignRole_userNotFound_fails() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> userService.assignRole("ghost", "ADMIN"));
        assertEquals("User does not exist", ex.getMessage());
    }
}