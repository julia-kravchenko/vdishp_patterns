package by.bsuir.patternslab.controller;

import by.bsuir.patternslab.entity.*;
import by.bsuir.patternslab.service.PublicationService;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

public class PublicationController {
    private final PublicationService service = PublicationService.getInstance();
    private final ResourceBundle resourceBundle;

    public PublicationController(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public String addPublication(PublicationType type, String title, Integer numberOfPages, String publishingHouse,
                                 Integer yearOfPublishing, List<String> authEdit) {

        PublishingHouse house = PublishingHouse.getByName(publishingHouse);
        if (title.isEmpty() || numberOfPages < 1 || yearOfPublishing < 1900
                || yearOfPublishing > Year.now().getValue() || house == null) {
            return resourceBundle.getString("incorrectDataMessage");
        } else {
            if (service.addPublication(type, title, numberOfPages, house, yearOfPublishing, authEdit)) {
                return resourceBundle.getString("successfulAdditionMessage");
            } else {
                return resourceBundle.getString("unsuccessfulAdditionMessage");
            }
        }
    }

    public String deletePublication(Integer isbn) {
        if (service.deletePublication(isbn)) {
            return resourceBundle.getString("successfulDeletingMessage");
        } else {
            return resourceBundle.getString("unsuccessfulDeletingMessage");
        }
    }

    public String printAllPublication() {
        Set<Publication> publications = service.findAll();
        return printPublications(publications);
    }

    public String printAllBooks() {
        Set<Publication> publications = service.findAllBooks();
        return printPublications(publications);
    }

    public String printAllMagazines() {
        Set<Publication> publications = service.findAllMagazines();
        return printPublications(publications);
    }

    private String printPublications(Set<Publication> publications) {
        StringBuilder result = new StringBuilder();
        if (publications.isEmpty()) {
            return result.append(resourceBundle.getString("emptyRepMessage")).toString();
        }
        for (Publication publication : publications) {
            result.append(publication.toString()).append("\n");
        }
        return result.toString();
    }

    public String findByIsbn(Integer isbn) {
        Optional<Publication> optionalPublication = service.findByIsbn(isbn);
        if (optionalPublication.isPresent()) {
            return optionalPublication.get().toString();
        } else {
            return resourceBundle.getString("searchFailedMessage");
        }
    }

    public String findByTitle(String title) {
        Set<Publication> publications = service.findByTitle(title);
        return searchResult(publications);
    }

    public String findByTitleAndPublishingHouse(String title, String publishingHouse) {
        Set<Publication> publications = service.findByTitleAndPublishingHouse(title, publishingHouse);
        return searchResult(publications);
    }

    public String findByTitleAndYearOfPublishing(String title, Integer yearOfPublishing) {
        Set<Publication> publishing = service.findByTitleAndYear(title, yearOfPublishing);
        return searchResult(publishing);
    }

    public String findByAuthor(List<String> authors) {
        Set<Publication> publications = service.findByAuthOrEdit(authors);
        return searchResult(publications);
    }

    public String findByPublishingHouse(String publishingHouse) {
        Set<Publication> publications = service.findByPublishingHouse(publishingHouse);
        return searchResult(publications);
    }

    private String searchResult(Set<Publication> publications) {
        if (publications.isEmpty()) {
            return resourceBundle.getString("searchFailedMessage");
        }
        StringBuilder result = new StringBuilder();
        for (Publication publication : publications) {
            result.append(publication.toString()).append("\n");
        }
        return result.toString();
    }

    private String sortResult(List<? extends Publication> publications) {
        if (publications.isEmpty()) {
            return resourceBundle.getString("emptyRepMessage");
        }
        StringBuilder result = new StringBuilder();
        for (Publication publication : publications) {
            result.append(publication.toString()).append("\n");
        }
        return result.toString();
    }

    public String sortByTitle() {
        List<Publication> publications = service.sortPublicationsByTitle();
        return sortResult(publications);
    }

    public String sortBookByAuthor() {
        List<Book> publications = service.sortBooksByAuthors();
        return sortResult(publications);
    }

    public String sortMagazinesByEditor() {
        List<Magazine> publications = service.sortMagazineByEditors();
        return sortResult(publications);
    }

    public String sortByPublishingHouse() {
        List<Publication> publications = service.sortPublicationsByPublishingHouse();
        return sortResult(publications);
    }

    public String sortByNumberOfPages() {
        List<Publication> publications = service.sortPublicationsByNumberOfPages();
        return sortResult(publications);
    }

    public String sortByYearOfPublishing() {
        List<Publication> publications = service.sortPublicationsByYearOfPublishing();
        return sortResult(publications);
    }

    public String updateTitlePublication(Integer isbn, String title) {
        if (service.updateTitlePublication(isbn, title)) {
            return resourceBundle.getString("successfulUpdatingMessage");
        } else {
            return resourceBundle.getString("unsuccessfulUpdatingMessage");
        }
    }

    public String updateYearOfPublication(Integer isbn, Integer year) {
        if (service.updateYearOfPublication(isbn, year)) {
            return resourceBundle.getString("successfulUpdatingMessage");
        } else {
            return resourceBundle.getString("unsuccessfulUpdatingMessage");
        }
    }

    public String updatePublishingHouse(Integer isbn, String house) {
        if (service.updatePublishingHouse(isbn, house)) {
            return resourceBundle.getString("successfulUpdatingMessage");
        } else {
            return resourceBundle.getString("unsuccessfulUpdatingMessage");
        }
    }
}
