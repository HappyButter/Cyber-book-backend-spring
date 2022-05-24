package zti.cyberbook.book;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;


public interface BookRepository extends Neo4jRepository<Book, Long> {

    Optional<Book> findBookByISBN(Double isbn);

}
