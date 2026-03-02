package M8_Activity7;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WelcomeController {
    @Value("${welcome.message:Hello from default profile}")
    private String message;
    @GetMapping("/api/welcome")
    public String welcome() { return message; }
}
