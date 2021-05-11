package by.bsuir.patternslab.entity;

import java.io.Serializable;
import java.util.Objects;

public abstract class Publication implements Serializable {

    private static Integer generateId = 1;

    private transient Integer isbn;
    private String title;
    private Integer numberOfPages;
    private PublishingHouse publishingHouse;
    private Integer yearOfPublishing;

    protected Publication() {
    }

    protected Publication(String title, Integer numberOfPages, PublishingHouse publishingHouse, Integer yearOfPublishing) {
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.publishingHouse = publishingHouse;
        this.yearOfPublishing = yearOfPublishing;
    }

    public static Integer getGenerateId() {
        return generateId++;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public Integer getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(Integer yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(numberOfPages, that.numberOfPages) &&
                Objects.equals(publishingHouse, that.publishingHouse) &&
                Objects.equals(yearOfPublishing, that.yearOfPublishing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, numberOfPages, publishingHouse, yearOfPublishing);
    }

    @Override
    public String toString() {
        return "Publication{" +
                "isbn=" + generateId +
                ", title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", publishingHouse='" + publishingHouse.getName() + '\'' +
                ", yearOfPublishing=" + yearOfPublishing +
                '}';
    }
}
