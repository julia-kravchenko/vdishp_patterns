package by.bsuir.patternslab.entity.observer;

import by.bsuir.patternslab.entity.Publication;

public interface Observed {
    boolean addObserver(Subscriber subscriber);
    boolean deleteObserver(Subscriber subscriber);
    void notifyObservers(Publication publication);
}
