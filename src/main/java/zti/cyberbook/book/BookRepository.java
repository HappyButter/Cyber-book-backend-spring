package zti.cyberbook.book;

import org.neo4j.driver.internal.value.ListValue;
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

    @Query("MATCH (a:User {id: $userId})" +
            " MATCH (b:Book {ISBN: $isbn})" +
            " MERGE (a)-[r:REVIEWED {rate: $rate, review: $review, creationDate: $creationDate}]->(b)" +
            " RETURN r")
    void addReview(String userId, Double isbn, Integer rate, String review, long creationDate);

    @Query("MATCH (b:Book {ISBN: $isbn})<-[r:REVIEWED]-()" +
            " WITH avg(r.rate) AS averageRate, count(*) as countReviews, b" +
            " SET b.rate = averageRate, b.voteCount = countReviews" +
            " RETURN averageRate")
    void updateBookRate(Double isbn);

    @Query("MATCH (b:Book {ISBN: $isbn})<-[r:REVIEWED]-(u:User)" +
            "RETURN collect({" +
            " rate: r.rate," +
            " review: r.review," +
            " creationDate: r.creationDate," +
            " authorFirstName: u.firstName," +
            " authorLastName: u.lastName," +
            " authorId: u.id" +
            " })")
    List<ListValue> getBookReviewsByISBN(Double isbn);

}
