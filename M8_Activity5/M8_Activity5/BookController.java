package M8_Activity5;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class BookController {

    private final Map<Long, Book> books = new LinkedHashMap<>();

    public BookController() {
        books.put(1L, new Book(1L, "Clean Code", "Robert C. Martin", 2008));
        books.put(2L, new Book(2L, "Effective Java", "Joshua Bloch", 2018));
        books.put(3L, new Book(3L, "Spring in Action", "Craig Walls", 2022));
    }

    @GetMapping("/api/books")
    @ResponseBody
    public List<Book> getAll() {
        return new ArrayList<>(books.values());
    }
}