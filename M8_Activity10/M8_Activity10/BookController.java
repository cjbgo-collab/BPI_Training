package M8_Activity10;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository repo;

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<BookDTO> getAll() {
        List<BookDTO> list = new ArrayList<>();
        for (Book b : repo.findAll()) {
            list.add(new BookDTO(b.getTitle(), b.getAuthor()));
        }
        return list;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable Long id) {
        return repo.findById(id)
                .map(b -> ResponseEntity.ok(new BookDTO(b.getTitle(), b.getAuthor())))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody BookCreateRequest req) {
        Book saved = repo.save(new Book(req.getTitle(), req.getAuthor()));
        return ResponseEntity.ok(new BookDTO(saved.getTitle(), saved.getAuthor()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}