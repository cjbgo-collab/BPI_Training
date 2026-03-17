package M9_Activity5;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class DashboardController {

    @GetMapping("/home")
    public String home() {
        return "Welcome to the portal";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "User dashboard";
    }

    @GetMapping("/reports")
    public String reports() {
        return "Manager reports";
    }

  
    @GetMapping("/profile/username/{username}")
    public String getProfileByUsername(@PathVariable("username") String username,
                                       Authentication authentication) {

        String loggedInUser = authentication.getName();

        boolean isManager = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_MANAGER"));

         
        if (!SecurityConfig.VALID_USERNAMES.contains(username)) {
            return "Forbidden: Username '" + username + "' does not exist.";
        }

      
        if (!isManager && !username.equals(loggedInUser)) {
            return "Forbidden: You cannot access another user's profile.";
        }

        
        return "Profile of " + username;
    }


   
}