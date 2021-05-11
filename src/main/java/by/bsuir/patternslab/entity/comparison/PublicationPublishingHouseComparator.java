package by.bsuir.patternslab.entity.comparison;

import by.bsuir.patternslab.entity.Publication;

import java.io.Serializable;
import java.util.Comparator;

public class PublicationPublishingHouseComparator implements Comparator<Publication>, Serializable {
    @Override
    public int compare(Publication o1, Publication o2) {
        return o1.getPublishingHouse().getName().compareTo(o2.getPublishingHouse().getName());
    }
}
