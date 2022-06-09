package zti.cyberbook.book;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zti.cyberbook.genre.Genre;
import zti.cyberbook.genre.GenreRepository;
import zti.cyberbook.author.Author;
import zti.cyberbook.author.AuthorRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public Map<String, String> addBook(RequestBodyBookObject requestBodyBookObject) {

        ArrayList<String> genres = requestBodyBookObject.getGenres();
        ArrayList<Genre> genresToSave = createOrUpdateGenresByNames(genres);

        String authorName = requestBodyBookObject.getAuthor();
        Author author = createOrUpdateAuthor(authorName);

        Book newBookItem = createOrUpdateBook(requestBodyBookObject, genresToSave, new ArrayList<>(List.of(author)));

        HashMap<String, String> map = new HashMap<>();
        map.put("success", "true");
        map.put("data", newBookItem.toString());
        return map;
    }

    ArrayList<Genre> createOrUpdateGenresByNames(ArrayList<String> genres) {
        ArrayList<Genre> genresToSave = new ArrayList<>();

        for (String genreName : genres) {
            Optional<Genre> newGenre = genreRepository.findGenreByName(genreName);

            if (newGenre.isEmpty()) {
                newGenre = Optional.of(new Genre());
                newGenre.get().setName(genreName);
            }

            genresToSave.add(newGenre.get());
        }

        genreRepository.saveAll(genresToSave);

        return genresToSave;
    }

    Author createOrUpdateAuthor(String authorName) {

        Optional<Author> authorItem = authorRepository.findAuthorByName(authorName);

        if (authorItem.isEmpty()) {
            Author newAuthor = new Author();
            newAuthor.setName(authorName);

            authorItem = Optional.of(authorRepository.save(newAuthor));
        }

        return authorItem.get();
    }

    Book createOrUpdateBook(RequestBodyBookObject requestBodyBookObject, ArrayList<Genre> genresToSave, ArrayList<Author> authors) {

        Optional<Book> book = bookRepository.findBookByISBN(requestBodyBookObject.getISBN());

        if(book.isEmpty()) {
            book = Optional.of(new Book());
            book.get().setISBN(requestBodyBookObject.getISBN());
        }

        book.get().setPageCount(requestBodyBookObject.getPage_count());
        book.get().setRate((double) 0);
        book.get().setVoteCount((double) 0);
        book.get().setPublisher(requestBodyBookObject.getPublisher());
        book.get().setDescription(requestBodyBookObject.getDescription());
        book.get().setLanguage(requestBodyBookObject.getLanguage());
        book.get().setTitle(requestBodyBookObject.getTitle());
        book.get().setPublished_date(requestBodyBookObject.getPublished_date());
        book.get().setGenres(genresToSave);
        book.get().setAuthors(authors);

        return bookRepository.save(book.get());
    }

    public List<Book> getBestRatedBooks() {

        List<Long> ids = bookRepository.findBestRatedBooks()
                .stream()
                .map(Book::getId)
                .collect(Collectors.toList());

        return bookRepository.findBooksByIdIn(ids);
    }

    public Book getBookByISBN(String isbn) {
        Optional<Book> book = bookRepository.findBookByISBN(Double.parseDouble(isbn));
        return book.orElse(null);
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findBooksByTitleLikeIgnoreCase(title);

    }
}
