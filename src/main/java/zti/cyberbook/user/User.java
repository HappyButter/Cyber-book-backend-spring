package zti.cyberbook.user;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import zti.cyberbook.book.Book;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Node
public class User {
    @Id @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Relationship(type = "FOLLOWS", direction = OUTGOING)
    private List<User> followedUsers = new ArrayList<>();

    @Relationship(type = "REVIEWED", direction = OUTGOING)
    private List<Book> reviewedBooks = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getFollowedUsers() {
        return followedUsers;
    }

    public void setFollowedUsers(List<User> followedUsers) {
        this.followedUsers = followedUsers;
    }

    public List<Book> getReviewedBooks() {
        return reviewedBooks;
    }

    public void setReviewedBooks(List<Book> reviewedBooks) {
        this.reviewedBooks = reviewedBooks;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", firstName:'" + firstName + '\'' +
                ", lastName:'" + lastName + '\'' +
                ", email:'" + email + '\'' +
                ", password:'" + password + '\'' +
                '}';
    }
}
