package M8_Activity1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    // Static in-memory data store (3 books)
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
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        Book found = books.get(id);
        if (found == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(found);
    }
}