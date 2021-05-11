package by.bsuir.patternslab.entity.comparison;

import by.bsuir.patternslab.entity.Book;

import java.io.Serializable;
import java.util.Comparator;

public class BookAuthorComparator implements Comparator<Book>, Serializable {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getAuthors().get(0).compareTo(o2.getAuthors().get(0));
    }
}
