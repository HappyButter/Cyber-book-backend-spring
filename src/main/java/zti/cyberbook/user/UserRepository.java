package zti.cyberbook.user;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface UserRepository extends Neo4jRepository<User, Long> {

    boolean existsByEmail(String email);

    User findUserByEmailAndAndPassword(String email, String password);
}
