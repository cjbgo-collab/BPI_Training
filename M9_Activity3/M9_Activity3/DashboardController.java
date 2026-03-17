package M9_Activity3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Welcome to the Dashboard (BCrypt version)!";
    }
}