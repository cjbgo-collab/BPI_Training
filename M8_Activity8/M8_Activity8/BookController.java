package M8_Activity8;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final Map<Long, Book> books = new LinkedHashMap<>();

    public BookController() {
        books.put(1L, new Book(1L, "Clean Code", "Robert C. Martin"));
        books.put(2L, new Book(2L, "Effective Java", "Joshua Bloch"));
        books.put(3L, new Book(3L, "Spring in Action", "Craig Walls"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable Long id) {
        Book b = books.get(id);

        if (b == null) {
            return ResponseEntity.notFound().build();
        }

        BookDTO dto = new BookDTO(b.getTitle(), b.getAuthor());
        return ResponseEntity.ok(dto);
    }
}