package M8_Activity2;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final Map<Long, Book> books = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    public BookController() {
        // seed 3 books
        books.put(1L, new Book(1L, "Clean Code", "Robert C. Martin", 2008));
        books.put(2L, new Book(2L, "Effective Java", "Joshua Bloch", 2018));
        books.put(3L, new Book(3L, "Spring in Action", "Craig Walls", 2022));
        seq.set(3);
    }

    // GET /api/books
    @GetMapping
    public List<Book> getAll() {
        return new ArrayList<>(books.values());
    }

    // GET /api/books/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") Long id) {
        Book b = books.get(id);
        return (b == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(b);
    }

    // POST /api/books  (create new book)
    @PostMapping
    public ResponseEntity<Book> create(@RequestBody @Valid BookCreateRequest req) {
        long id = seq.incrementAndGet();
        Book saved = new Book(id, req.getTitle(), req.getAuthor(), req.getYear());
        books.put(id, saved);

        // 201 Created + Location header + body
        return ResponseEntity
                .created(URI.create("/api/books/" + id))
                .body(saved);
    }
}