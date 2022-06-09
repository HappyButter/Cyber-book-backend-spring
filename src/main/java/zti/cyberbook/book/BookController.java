package zti.cyberbook.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @GetMapping("/best-ratings")
    List<Book> getBestRatedBooks() {
        return bookService.getBestRatedBooks();
    }

    @GetMapping("/isbn/{isbn}")
    Book getBookByISBN(@PathVariable String isbn){
        return bookService.getBookByISBN(isbn);
    }

    @PostMapping("/title")
    List<Book> getBooksByTitle(@RequestBody Map<String, String> reqBody){
        String title = reqBody.get("title");
        return bookService.getBooksByTitle(title);
    }

//    @PostMapping("/review")
//    boolean addBookReview(@RequestBody Map<String, String> reqBody)
}
