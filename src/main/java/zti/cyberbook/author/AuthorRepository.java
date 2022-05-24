package zti.cyberbook.author;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface AuthorRepository extends Neo4jRepository<Author, Long> {

    Optional<Author> findAuthorByName(String name);

}
