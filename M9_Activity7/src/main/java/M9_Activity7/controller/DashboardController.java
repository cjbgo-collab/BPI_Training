package M9_Activity7.controller;

import M9_Activity7.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class DashboardController {

    private final UserRepository repo;

    public DashboardController(UserRepository repo) {
        this.repo = repo;
    }
    
    

    @GetMapping("/home")
    public String home() { return "Welcome"; }

    @GetMapping("/dashboard")
    public String dashboard() { return "User dashboard"; }

    @GetMapping("/reports")
    public String reports() { return "Manager reports"; }


@GetMapping("/profile/username/{username}")
public String profile(@PathVariable("username") String username,
                      Authentication auth) {

    boolean exists = repo.findByUsername(username).isPresent();
    if (!exists)
        return "Forbidden: Username does not exist";

    String logged = auth.getName();

    boolean isManager = auth.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_MANAGER"));

    if (!isManager && !logged.equals(username))
        return "Forbidden: You cannot access another user's profile";

    return "Profile of " + username;
}


    
}