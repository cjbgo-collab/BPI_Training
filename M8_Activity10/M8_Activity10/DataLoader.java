package M8_Activity10;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final BookRepository repo;

    public DataLoader(BookRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        repo.save(new Book("Clean Code", "Robert C. Martin"));
        repo.save(new Book("Effective Java", "Joshua Bloch"));
        repo.save(new Book("Spring in Action", "Craig Walls"));
        repo.save(new Book("Domain-Driven Design", "Eric Evans"));
        repo.save(new Book("Refactoring", "Martin Fowler"));
        repo.save(new Book("Test-Driven Development", "Kent Beck"));
    }
}