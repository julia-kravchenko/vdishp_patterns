package by.bsuir.patternslab.entity;

import java.util.List;
import java.util.Objects;

public class Book extends Publication {
    private static final long serialVersionUID = 2572649278462299286L;

    private List<String> authors;

    public Book() {}

    public Book(String title, Integer numberOfPages, PublishingHouse publishingHouse, Integer yearOfPublishing) {
        super(title, numberOfPages, publishingHouse, yearOfPublishing);
    }

    public Book(String title, Integer numberOfPages, PublishingHouse publishingHouse, Integer yearOfPublishing, List<String> authors) {
        super(title, numberOfPages, publishingHouse, yearOfPublishing);
        this.authors = authors;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(authors, book.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), authors);
    }

    @Override
    public String toString() {
        return "Book {" +
                "id=" + super.getIsbn() +
                ", title='" + super.getTitle() + '\'' +
                ", numberOfPages=" + super.getNumberOfPages() +
                ", publishingHouse='" + super.getPublishingHouse().getName() + '\'' +
                ", yearOfPublishing=" + super.getYearOfPublishing() +
                ", authors=" + authors +
                '}';
    }
}
