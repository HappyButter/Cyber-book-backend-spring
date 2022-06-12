package zti.cyberbook.user;

import org.neo4j.driver.internal.value.ListValue;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User, String> {

    @Query("MATCH (a:User)" +
            " MATCH (b:User)" +
            " WHERE a.id=$userId AND b.id=$userToFollowId" +
            " MERGE (a)-[r:FOLLOWS]->(b)" +
            " RETURN a, r")
    User followUser(String userId, String userToFollowId);

    @Query("MATCH (a:User)-[r:FOLLOWS]->(b:User)" +
            " WHERE a.id=$userId AND b.id=$userToFollowId" +
            " DELETE r")
    User unfollowUser(String userId, String userToFollowId);


    @Query("MATCH (u:User)" +
            " WHERE ( toLower(u.firstName + u.lastName) CONTAINS trim(toLower($name))" +
            " OR toLower(u.lastName + u.firstName) CONTAINS trim(toLower($name)) )" +
            " RETURN u")
    List<User> findAllUsersByName(String name);

    List<User> findUsersByIdIn(List<String> ids);

    User findUserById(String id);

    @Query("MATCH (u:User {id: $userId})-[:FOLLOWS]->(u2:User)-[r:REVIEWED]->(b:Book)<-[:WROTE]-(a:Author)" +
            " WITH collect({firstName: u2.firstName, lastName: u2.lastName, authorId: u2.id}) as author, " +
            " collect({ISBN: b.ISBN, rate: b.rate, title: b.title, author: a.name}) as book, r" +
            " RETURN collect({author: author, book: book, rate: r.rate, review: r.review, creationDate: r.creationDate})")
    List<ListValue> getFollowedUsersReviews(String userId);
}
