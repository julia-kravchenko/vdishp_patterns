package by.bsuir.patternslab.controller;

import by.bsuir.patternslab.entity.PublicationType;
import by.bsuir.patternslab.entity.observer.Subscriber;
import by.bsuir.patternslab.service.SubscriberService;

import java.util.Optional;
import java.util.ResourceBundle;

public class SubscriberController {
    private final SubscriberService service = SubscriberService.getInstance();
    private ResourceBundle resourceBundle;

    public SubscriberController(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public String subscribe(String name, PublicationType type, Integer yearOfPublishing) {
        if (service.subscribe(name, type, yearOfPublishing)) {
            return resourceBundle.getString("successfulSubscribeMessage");
        } else {
            return resourceBundle.getString("unsuccessfulSubscribeMessage");
        }
    }

    public String unsubscribe(String name) {
        if (service.unsubscribe(name)) {
            return resourceBundle.getString("successfulUnsubscribeMessage");
        } else {
            return resourceBundle.getString("unsuccessfulUnsubscribeMessage");
        }
    }

    public String viewRepositoryStatus(String name) {
        Optional<Subscriber> optional = service.findByName(name);
        if (optional.isPresent()) {
            Subscriber subscriber = optional.get();
            StringBuilder result = new StringBuilder();
            result.append("Количество ");
            if (subscriber.getPublicationType() == PublicationType.BOOK) {
                result.append("книг ");
            } else {
                result.append("журналов ");
            }
            result.append(subscriber.getYearOfPublishing()).append(" года: ").append(subscriber.getNumberOfPublications());
            return result.toString();
        } else {
            return resourceBundle.getString("noSuchSubscriberMessage");
        }
    }
}
