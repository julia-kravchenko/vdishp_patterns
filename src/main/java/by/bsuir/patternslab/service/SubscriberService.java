package by.bsuir.patternslab.service;

import by.bsuir.patternslab.entity.PublicationType;
import by.bsuir.patternslab.entity.observer.Subscriber;
import by.bsuir.patternslab.entity.observer.SubscriberDecorator;
import by.bsuir.patternslab.service.repository.PublicationRepositoryImpl;

import java.util.Optional;

public class SubscriberService {
    private static final SubscriberService instance = new SubscriberService();
    PublicationRepositoryImpl repository = PublicationRepositoryImpl.getInstance();

    private SubscriberService() {

    }

    public static SubscriberService getInstance() {
        return instance;
    }

    public boolean subscribe(String name, PublicationType type, Integer yearOfPublishing) {
        Subscriber subscriber = null;
        if(repository.isRoundNumberOfSubscribers()) {
            subscriber = new SubscriberDecorator(name, type, yearOfPublishing);
        } else {
            subscriber = new Subscriber(name, type, yearOfPublishing);
        }
        return repository.addObserver(subscriber);
    }

    public boolean unsubscribe(String name) {
        Optional<Subscriber> subscriber = repository.findByName(name);
        return subscriber.filter(value -> repository.deleteObserver(value)).isPresent();
    }

    public Optional<Subscriber> findByName(String name) {
        return repository.findByName(name);
    }
}
