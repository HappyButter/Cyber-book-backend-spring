package zti.cyberbook.book;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import zti.cyberbook.genre.Genre;
import zti.cyberbook.author.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;
import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Node
public class Book {

    @Id @GeneratedValue
    private Long id;
    private Double ISBN;
    private Double pageCount;
    private Double rate;
    private Double voteCount;
    private String publisher;
    private String description;
    private String language;
    private String title;
    private String published_date;

    @Relationship(type = "WROTE", direction = INCOMING)
    private List<Author> authors = new ArrayList<>();

    @Relationship(type = "HAS_GENRE", direction = OUTGOING)
    private List<Genre> genres = new ArrayList<>();


    public List<String> getGenres() {
        return genres.stream().map(Genre::getName).collect(Collectors.toList());
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<String> getAuthors() {
        return authors.stream().map(Author::getName).collect(Collectors.toList());
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getISBN() {
        return ISBN.longValue();
    }

    public void setISBN(Double ISBN) {
        this.ISBN = ISBN;
    }

    public Double getPageCount() {
        return pageCount;
    }

    public void setPageCount(Double pageCount) {
        this.pageCount = pageCount;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Double voteCount) {
        this.voteCount = voteCount;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", ISBN:" + ISBN +
                ", pageCount:" + pageCount +
                ", rate:" + rate +
                ", voteCount:" + voteCount +
                ", publisher:'" + publisher + '\'' +
                ", description:'" + description + '\'' +
                ", language:'" + language + '\'' +
                ", title:'" + title + '\'' +
                ", published_date:'" + published_date + '\'' +
                ", authors:" + authors +
                ", genres:" + genres +
                '}';
    }
}
