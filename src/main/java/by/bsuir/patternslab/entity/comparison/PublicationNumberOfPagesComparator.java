package by.bsuir.patternslab.entity.comparison;

import by.bsuir.patternslab.entity.Publication;

import java.io.Serializable;
import java.util.Comparator;

public class PublicationNumberOfPagesComparator implements Comparator<Publication>, Serializable {
    @Override
    public int compare(Publication o1, Publication o2) {
        return o1.getNumberOfPages().compareTo(o2.getNumberOfPages());
    }
}
