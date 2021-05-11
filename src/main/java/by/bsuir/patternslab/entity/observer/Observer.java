package by.bsuir.patternslab.entity.observer;

import by.bsuir.patternslab.entity.Publication;

public interface Observer {
   void handleEvent(Publication publication);
}
