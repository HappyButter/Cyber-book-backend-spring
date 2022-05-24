package zti.cyberbook.book;

import java.util.ArrayList;
import java.util.List;

public class RequestBodyBookObject {
    private Long ISBN;
    private Integer page_count;
    private String publisher;
    private String description;
    private String language;
    private String title;
    private String published_date;
    private String author;
    private ArrayList<String> genres;

    public RequestBodyBookObject(Long ISBN, Integer page_count, String publisher, String description, String language, String title, String published_date, String author, ArrayList<String> genres) {
        this.ISBN = ISBN;
        this.page_count = page_count;
        this.publisher = publisher;
        this.description = description;
        this.language = language;
        this.title = title;
        this.published_date = published_date;
        this.author = author;
        this.genres = genres;
    }

    public Double getISBN() {
        return Double.parseDouble(ISBN.toString());
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    public Double getPage_count() {
        return Double.parseDouble(page_count.toString());
    }

    public void setPage_count(Integer page_count) {
        this.page_count = page_count;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "RequestBodyBookObject{" +
                "ISBN=" + ISBN +
                ", \npage_count=" + page_count +
                ", \npublisher='" + publisher + '\'' +
                ", \ndescription='" + description + '\'' +
                ", \nlanguage='" + language + '\'' +
                ", \ntitle='" + title + '\'' +
                ", \npublished_date='" + published_date + '\'' +
                ", \nauthor='" + author + '\'' +
                ", \ngenres=" + genres +
                '}';
    }
}
