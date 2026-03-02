package M8_Activity2;

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
        log.info("   Activity 2 is RUNNING. Try:");
        log.info("   GET  http://localhost:8080/api/books");
        log.info("   GET  http://localhost:8080/api/books/1");
        log.info("   POST http://localhost:8080/api/books");
    }
}