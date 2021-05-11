package by.bsuir.patternslab.entity;

import java.util.List;
import java.util.Objects;

public class Magazine extends Publication {
    private static final long serialVersionUID = -2060248488032665316L;

    private List<String> chiefEditors;

    public Magazine() {
    }

    public Magazine(String title, Integer numberOfPages, PublishingHouse publishingHouse, Integer yearOfPublishing) {
        super(title, numberOfPages, publishingHouse, yearOfPublishing);
    }

    public Magazine(String title, Integer numberOfPages, PublishingHouse publishingHouse, Integer yearOfPublishing, List<String> chiefEditors) {
        super(title, numberOfPages, publishingHouse, yearOfPublishing);
        this.chiefEditors = chiefEditors;
    }

    public List<String> getChiefEditors() {
        return chiefEditors;
    }

    public void setChiefEditors(List<String> chiefEditors) {
        this.chiefEditors = chiefEditors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Magazine magazine = (Magazine) o;
        return Objects.equals(chiefEditors, magazine.chiefEditors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), chiefEditors);
    }

    @Override
    public String toString() {
        return "Magazine {" +
                "id=" + super.getIsbn() +
                ", title='" + super.getTitle() + '\'' +
                ", numberOfPages=" + super.getNumberOfPages() +
                ", publishingHouse='" + super.getPublishingHouse().getName() + '\'' +
                ", yearOfPublishing=" + super.getYearOfPublishing() +
                ", chiefEditors=" + chiefEditors +
                '}';
    }
}
