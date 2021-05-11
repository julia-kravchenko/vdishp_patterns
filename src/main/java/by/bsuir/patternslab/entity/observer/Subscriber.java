package by.bsuir.patternslab.entity.observer;

import by.bsuir.patternslab.entity.Publication;
import by.bsuir.patternslab.entity.PublicationType;

import java.io.Serializable;

public class Subscriber implements Observer, Serializable {
    private static final long serialVersionUID = -6211394361260020547L;

    private String name;
    private Integer numberOfPublications;
    private PublicationType publicationType;
    private Integer yearOfPublishing;

    public Subscriber(String name) {
        this.name = name;
    }

    public Subscriber(String name, PublicationType publicationType, Integer yearOfPublishing) {
        this.name = name;
        this.publicationType = publicationType;
        this.yearOfPublishing = yearOfPublishing;
    }

    public Subscriber(String name, Integer numberOfPublications, PublicationType publicationType, Integer yearOfPublishing) {
        this.name = name;
        this.numberOfPublications = numberOfPublications;
        this.publicationType = publicationType;
        this.yearOfPublishing = yearOfPublishing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfPublications() {
        return numberOfPublications;
    }

    public void setNumberOfPublications(Integer numberOfPublications) {
        this.numberOfPublications = numberOfPublications;
    }

    public PublicationType getPublicationType() {
        return publicationType;
    }

    public void setPublicationType(PublicationType publicationType) {
        this.publicationType = publicationType;
    }

    public Integer getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(Integer yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    @Override
    public void handleEvent(Publication publication) {
        String typeStr = publication.getClass().toString();
        PublicationType type;
        if(typeStr.contains("Book")) {
            type = PublicationType.BOOK;
        } else {
            type = PublicationType.MAGAZINE;
        }
        if(yearOfPublishing.equals(publication.getYearOfPublishing()) && publicationType == type) {
            System.out.println("Dear " + name + "!\nWe have some changes in repository\n");
            //TODO записать в файл "name"Notice.txt
        }
    }
}
