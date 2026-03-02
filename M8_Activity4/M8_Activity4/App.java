package M8_Activity4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void ready() {
        log.info("   Activity 4 is RUNNING. Try these @RequestParam endpoints:");
        log.info("   GET http://localhost:8080/api/books"); // all
        log.info("   GET http://localhost:8080/api/books/find?id=2");
        log.info("   GET http://localhost:8080/api/books/findByTitle?title=Effective Java");
        log.info("   GET http://localhost:8080/api/books/findByAuthor?author=Robert%20C.%20Martin");
        log.info("   GET http://localhost:8080/api/books/findFirst  (uses default id=1)");
    }
}