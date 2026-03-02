package M8_Activity3;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final Map<Long, Book> books = new LinkedHashMap<>();

    public BookController() {
        books.put(1L, new Book(1L, "Clean Code", "Robert C. Martin", 2008));
        books.put(2L, new Book(2L, "Effective Java", "Joshua Bloch", 2018));
        books.put(3L, new Book(3L, "Spring in Action", "Craig Walls", 2022));
    }

    @GetMapping
    public List<Book> getAll() {
        return new ArrayList<>(books.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") Long id) {
        Book found = books.get(id);
        return (found == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(found);
    }
    
    @GetMapping("/author/{author}")
    public List<Book> getByAuthor(@PathVariable("author") String author) {
        String normalized = author.trim().toLowerCase();
        return books.values().stream()
                .filter(b -> b.getAuthor() != null && b.getAuthor().toLowerCase().equals(normalized))
                .collect(Collectors.toList());
    }


    @GetMapping("/year/{year}")
    public List<Book> getByYear(@PathVariable("year") Integer year) {
        return books.values().stream()
                .filter(b -> Objects.equals(b.getYear(), year))
                .collect(Collectors.toList());
    }
}