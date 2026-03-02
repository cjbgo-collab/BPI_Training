package M8_Activity9;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final Map<Long, Book> books = new LinkedHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    public BookController() {
        books.put(1L, new Book(1L, "Clean Code", "Robert C. Martin"));
        books.put(2L, new Book(2L, "Effective Java", "Joshua Bloch"));
        books.put(3L, new Book(3L, "Spring in Action", "Craig Walls"));
        seq.set(3);
    }

    @GetMapping
    public List<BookDTO> getAll() {
        List<BookDTO> list = new ArrayList<>();
        for (Book b : books.values()) {
            list.add(new BookDTO(b.getTitle(), b.getAuthor()));
        }
        return list;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable Long id) {
        Book b = books.get(id);
        if (b == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new BookDTO(b.getTitle(), b.getAuthor()));
    }

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody BookCreateRequest req) {
        long id = seq.incrementAndGet();
        Book newBook = new Book(id, req.getTitle(), req.getAuthor());
        books.put(id, newBook);
        return ResponseEntity.ok(new BookDTO(newBook.getTitle(), newBook.getAuthor()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Book removed = books.remove(id);
        if (removed == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}