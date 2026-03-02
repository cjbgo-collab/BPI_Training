package M8_Activity4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

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


    @GetMapping("/findByAuthor")
    public ResponseEntity<?> findByAuthor(@RequestParam(name = "author", required = false) String author) {
        log.info("findByAuthor (raw)   : '{}'", author);

        if (author == null || author.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Query parameter 'author' is required",
                    "example", "/api/books/findByAuthor?author=Robert%20C.%20Martin"
            ));
        }

        String decoded = URLDecoder.decode(author, StandardCharsets.UTF_8);
        String normQuery = normalize(decoded);
        log.info("findByAuthor (decoded): '{}'", decoded);
        log.info("findByAuthor (norm)   : '{}'", normQuery);

        return books.values().stream()
                .filter(b -> normalize(b.getAuthor()).equals(normQuery))
                .findFirst()
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(Map.of(
                        "error", "No book found for exact author",
                        "author_raw", author,
                        "author_decoded", decoded
                )));
    }


    @GetMapping("/findByAuthorLike")
    public List<Book> findByAuthorLike(@RequestParam(name = "author", required = false) String author) {
        log.info("findByAuthorLike (raw): '{}'", author);

        if (author == null || author.isBlank()) {
            return Collections.emptyList();
        }
        String q = normalize(URLDecoder.decode(author, StandardCharsets.UTF_8));

        return books.values().stream()
                .filter(b -> normalize(b.getAuthor()).contains(q))
                .collect(Collectors.toList());
    }


    private static String normalize(String s) {
        if (s == null) return "";
    
        return s.trim().replaceAll("\\s+", " ").toLowerCase();
    }
}