package by.bsuir.patternslab.service.specification.searchspec;

import by.bsuir.patternslab.entity.Publication;
import by.bsuir.patternslab.entity.PublishingHouse;

public class MatchPublicationPublishingHouseCriteria implements SearchCriteria<Publication>{
    private final PublishingHouse searchPublishingHouse;

    public MatchPublicationPublishingHouseCriteria(PublishingHouse publishingHouse) {
        this.searchPublishingHouse = publishingHouse;
    }

    @Override
    public boolean search(Publication obj) {
        return obj.getPublishingHouse().equals(searchPublishingHouse);
    }
}
