package zti.cyberbook.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zti.cyberbook.review.Review;

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
    Book getBookByISBN(@PathVariable String isbn) {
        return bookService.getBookByISBN(isbn);
    }

    @GetMapping("/reviews/{isbn}")
    List<Map<String, Object>> getBookReviewsByISBN(@PathVariable String isbn) {
        return bookService.getBookReviewsByISBN(isbn);
    }

    @PostMapping("/title")
    List<Book> getBooksByTitle(@RequestBody Map<String, String> reqBody) {
        String title = reqBody.get("title");
        return bookService.getBooksByTitle(title);
    }

    @PostMapping("/review")
    boolean addBookReview(@RequestBody Review review) {
        return bookService.addBookReview(review);
    }
}
