package M9_Activity4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}