package by.bsuir.patternslab.service.specification.searchspec;

import by.bsuir.patternslab.entity.Book;
import by.bsuir.patternslab.entity.Publication;

import java.util.List;

public class MatchBookAuthorCriteria implements SearchCriteria<Publication> {
    private final List<String> searchAuthors;

    public MatchBookAuthorCriteria(List<String> authors) {
        this.searchAuthors = authors;
    }

    @Override
    public boolean search(Publication obj) {
        Book book;
        if (!(obj instanceof Book)) {
            return false;
        }
        book = (Book) obj;
        for (String author : book.getAuthors()) {
            for (String searchAuthor : searchAuthors) {
                if (author.toUpperCase().contains(searchAuthor.toUpperCase())) {
                    return true;
                }
            }
        }
        return false;
    }
}
