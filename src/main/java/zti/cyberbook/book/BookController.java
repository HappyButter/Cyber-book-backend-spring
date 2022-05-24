package zti.cyberbook.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/all")
    List<Book> getAll() {
        return bookService.getAllBooks();
    }

    @PostMapping
    Map<String, String> addBook(@RequestBody RequestBodyBookObject requestBodyBookObject) {
        return bookService.addBook(requestBodyBookObject);
    }
}
