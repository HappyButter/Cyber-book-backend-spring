package zti.cyberbook.book;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends Neo4jRepository<Book, Long> {

    @Override
    Optional<Book> findById(Long id);

    Optional<Book> findBookByISBN(Double isbn);
    @Query("MATCH (b:Book) RETURN b ORDER BY b.rate DESC LIMIT 6")
    List<Book> findBestRatedBooks();

    List<Book> findBooksByIdIn(List<Long> ids);

    List<Book> findBooksByTitleLikeIgnoreCase(String title);
}
