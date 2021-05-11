package by.bsuir.patternslab.service.factory;

import by.bsuir.patternslab.entity.Book;
import by.bsuir.patternslab.entity.Magazine;
import by.bsuir.patternslab.entity.Publication;
import by.bsuir.patternslab.entity.PublicationType;
import by.bsuir.patternslab.entity.PublishingHouse;

import java.util.List;

public class PublicationFactory {
    public Publication create(PublicationType type) {
        if (type == PublicationType.BOOK) {
            return new Book();
        } else if (type == PublicationType.MAGAZINE) {
            return new Magazine();
        }
        return null;
    }

    public Publication create(PublicationType type, String title, Integer numberOfPages,
                                            PublishingHouse publishingHouse, Integer yearOfPublishing,
                                            List<String> author) {
        if (type == PublicationType.BOOK) {
            return new Book(title, numberOfPages, publishingHouse, yearOfPublishing, author);
        } else if (type == PublicationType.MAGAZINE) {
            return new Magazine(title, numberOfPages, publishingHouse, yearOfPublishing, author);
        }
        return null;
    }
}
