package by.bsuir.patternslab.service;

import by.bsuir.patternslab.entity.Book;
import by.bsuir.patternslab.entity.Magazine;
import by.bsuir.patternslab.entity.Publication;
import by.bsuir.patternslab.entity.PublicationType;
import by.bsuir.patternslab.entity.PublishingHouse;
import by.bsuir.patternslab.service.factory.PublicationFactory;
import by.bsuir.patternslab.service.repository.PublicationRepositoryImpl;
import by.bsuir.patternslab.service.specification.searchspec.ConjunctionCriteria;
import by.bsuir.patternslab.service.specification.searchspec.DisjunctionCriteria;
import by.bsuir.patternslab.service.specification.searchspec.MatchBookAuthorCriteria;
import by.bsuir.patternslab.service.specification.searchspec.MatchMagazineEditorCriteria;
import by.bsuir.patternslab.service.specification.searchspec.MatchPublicationIsbnCriteria;
import by.bsuir.patternslab.service.specification.searchspec.MatchPublicationPublishingHouseCriteria;
import by.bsuir.patternslab.service.specification.searchspec.MatchPublicationTitleCriteria;
import by.bsuir.patternslab.service.specification.searchspec.MatchPublicationYearOfPublishingCriteria;
import by.bsuir.patternslab.service.specification.sortspec.SortBookAuthorCriteria;
import by.bsuir.patternslab.service.specification.sortspec.SortMagazineEditorCriteria;
import by.bsuir.patternslab.service.specification.sortspec.SortPublicationNumberOfPagesCriteria;
import by.bsuir.patternslab.service.specification.sortspec.SortPublicationPublishingHouseCriteria;
import by.bsuir.patternslab.service.specification.sortspec.SortPublicationTitleCriteria;
import by.bsuir.patternslab.service.specification.sortspec.SortPublicationYearOfPublishingCriteria;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class PublicationService {
    private static final PublicationService instance = new PublicationService();
    PublicationRepositoryImpl repository = PublicationRepositoryImpl.getInstance();

    private PublicationService() {
    }

    public static PublicationService getInstance() {
        return instance;
    }

    public boolean addPublication(PublicationType type, String title, Integer numberOfPages,
                                  PublishingHouse publishingHouse, Integer yearOfPublishing,
                                  List<String> authEdit) {
        PublicationFactory factory = new PublicationFactory();
        return repository.save(factory.create(type, title, numberOfPages, publishingHouse, yearOfPublishing, authEdit));
    }

    public boolean deletePublication(Integer isbn) {
        Optional<Publication> publicationOptional = findByIsbn(isbn);
        return publicationOptional.filter(publication -> repository.delete(publication)).isPresent();
    }

    public Set<Publication> findAll() {
        return repository.findAll();
    }

    public Set<Publication> findAllBooks() {
        Set<Publication> publications = new HashSet<>(findAll());
        publications.removeIf(publication -> !(publication instanceof Book));
        return publications;
    }

    public Set<Publication> findAllMagazines() {
        Set<Publication> publications = new HashSet<>(findAll());
        publications.removeIf(publication -> !(publication instanceof Magazine));
        return publications;
    }

    public Optional<Publication> findByIsbn(Integer isbn) {
        Optional<Publication> publicationOptional = Optional.empty();
        for (Publication publication : repository.find(new MatchPublicationIsbnCriteria(isbn))) {
            publicationOptional = Optional.of(publication);
        }
        return publicationOptional;
    }

    public Set<Publication> findByTitle(String title) {
        return repository.find(new MatchPublicationTitleCriteria(title));
    }

    public Set<Publication> findByAuthOrEdit(List<String> names) {
        return repository.find(new DisjunctionCriteria<>(Arrays.asList(new MatchBookAuthorCriteria(names),
                new MatchMagazineEditorCriteria(names))));
    }

    public Set<Publication> findByPublishingHouse(String publishingHouse) {
        return repository.find(new MatchPublicationPublishingHouseCriteria(PublishingHouse.getByName(publishingHouse)));
    }

    public Set<Publication> findByTitleAndPublishingHouse(String title, String publishingHouse) {
        return repository.find(new ConjunctionCriteria<>(Arrays.asList(new MatchPublicationTitleCriteria(title),
                new MatchPublicationPublishingHouseCriteria(PublishingHouse.getByName(publishingHouse)))));
    }

    public Set<Publication> findByTitleAndYear(String title, Integer year) {
        return repository.find(new ConjunctionCriteria<>(Arrays.asList(new MatchPublicationTitleCriteria(title),
                new MatchPublicationYearOfPublishingCriteria(year))));
    }

    public List<Publication> sortPublicationsByTitle() {
        return repository.sort(new SortPublicationTitleCriteria());
    }

    public List<Publication> sortPublicationsByPublishingHouse() {
        return repository.sort(new SortPublicationPublishingHouseCriteria());
    }

    public List<Publication> sortPublicationsByNumberOfPages() {
        return repository.sort(new SortPublicationNumberOfPagesCriteria());
    }

    public List<Publication> sortPublicationsByYearOfPublishing() {
        return repository.sort(new SortPublicationYearOfPublishingCriteria());
    }

    public List<Book> sortBooksByAuthors() {
        Set<Book> books = new TreeSet<>();
        for (Publication publication : findAllBooks()) {
            if (publication instanceof Book) {
                books.add((Book) publication);
            }
        }
        return new SortBookAuthorCriteria().sort(books);
    }

    public List<Magazine> sortMagazineByEditors() {
        Set<Magazine> magazines = new TreeSet<>();
        for (Publication publication : findAllBooks()) {
            if (publication instanceof Magazine) {
                magazines.add((Magazine) publication);
            }
        }
        return new SortMagazineEditorCriteria().sort(magazines);
    }

    public boolean updateTitlePublication(Integer isbn, String title) {
        Optional<Publication> optionalPublication = findByIsbn(isbn);
        if (optionalPublication.isPresent()) {
            optionalPublication.get().setTitle(title);
            return repository.update(optionalPublication.get());
        } else {
            return false;
        }
    }

    public boolean updateYearOfPublication(Integer isbn, Integer year) {
        Optional<Publication> optionalPublication = findByIsbn(isbn);
        if (optionalPublication.isPresent()) {
            optionalPublication.get().setYearOfPublishing(year);
            return repository.update(optionalPublication.get());
        } else {
            return false;
        }
    }

    public boolean updatePublishingHouse(Integer isbn, String house) {
        Optional<Publication> optionalPublication = findByIsbn(isbn);
        if (optionalPublication.isPresent()) {
            optionalPublication.get().setPublishingHouse(PublishingHouse.getByName(house));
            return repository.update(optionalPublication.get());
        } else {
            return false;
        }
    }
}
