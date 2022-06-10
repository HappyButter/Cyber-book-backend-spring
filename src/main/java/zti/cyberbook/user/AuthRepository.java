package zti.cyberbook.user;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AuthRepository extends Neo4jRepository<User, String> {

    boolean existsByEmail(String email);

    User findUserByEmailAndPassword(String email, String password);
}
