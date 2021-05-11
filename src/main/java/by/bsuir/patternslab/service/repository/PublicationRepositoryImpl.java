package by.bsuir.patternslab.service.repository;

import by.bsuir.patternslab.dao.PublicationDaoImpl;
import by.bsuir.patternslab.entity.Publication;
import by.bsuir.patternslab.entity.PublicationType;
import by.bsuir.patternslab.entity.comparison.PublicationPublishingHouseComparator;
import by.bsuir.patternslab.entity.comparison.PublicationTitleComparator;
import by.bsuir.patternslab.entity.observer.Observed;
import by.bsuir.patternslab.entity.observer.Subscriber;
import by.bsuir.patternslab.service.specification.searchspec.SearchCriteria;
import by.bsuir.patternslab.service.specification.sortspec.SortCriteria;
import by.bsuir.patternslab.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class PublicationRepositoryImpl implements Repository<Publication>, Observed {
    private static final PublicationRepositoryImpl instance = new PublicationRepositoryImpl();
    private final PublicationDaoImpl publicationDao = PublicationDaoImpl.getInstance();
    private final Logger logger = LogManager.getLogger(PublicationRepositoryImpl.class);

    private Set<Publication> publications;
    private List<Subscriber> subscribers;

    private PublicationRepositoryImpl() {
        Comparator<Publication> comparing = new PublicationTitleComparator().thenComparing(new PublicationPublishingHouseComparator());
        publications = new TreeSet<>(comparing);
        load();
        subscribers = new ArrayList<>();
        subscribers = readObserversFromFile();
    }

    public static PublicationRepositoryImpl getInstance() {
        return instance;
    }

    @Override
    public boolean save(Publication obj) {
        if(savePublicationToRepository(obj)) {
            notifyObservers(obj);
            return publicationDao.save(publications);
        }
        return false;
    }

    @Override
    public void load() {
        Set<Publication> loadPublications = publicationDao.load();
        for(Publication publication : loadPublications) {
            savePublicationToRepository(publication);
        }
    }

    private boolean savePublicationToRepository(Publication obj) {
        if (obj.getTitle().isEmpty() || obj.getNumberOfPages() < 1 || obj.getYearOfPublishing() < 1900
                || obj.getYearOfPublishing() > Year.now().getValue() || obj.getPublishingHouse() == null) {
            return false;
        }
        if (!publications.contains(obj)) {
            obj.setIsbn(Publication.getGenerateId());
            publications.add(obj);
            return true;
        }
        return false;
    }

    @Override
    public Set<Publication> findAll() {
        return publications;
    }

    @Override
    public Set<Publication> find(SearchCriteria<Publication> searchCriteria) {
        Comparator<Publication> comparing = new PublicationTitleComparator().thenComparing(new PublicationPublishingHouseComparator());
        Set<Publication> result = new TreeSet<>(comparing);

        for (Publication publication : publications) {
            if (searchCriteria.search(publication)) {
                result.add(publication);
            }
        }
        return result;
    }

    @Override
    public List<Publication> sort(SortCriteria<Publication> sortCriteria) {
        return sortCriteria.sort(publications);
    }

    @Override
    public boolean delete(Publication obj) {
        if (publications.contains(obj)) {
            publications.remove(obj);
            notifyObservers(obj);
            return publicationDao.delete(publications);
        }
        return false;
    }

    @Override
    public boolean update(Publication obj) {
        if (obj.getTitle().isEmpty() || obj.getNumberOfPages() < 1 || obj.getYearOfPublishing() < 1900
                || obj.getYearOfPublishing() > Year.now().getValue() || obj.getPublishingHouse() == null) {
            return false;
        } else {
            publications.removeIf(publication -> obj.getIsbn().equals(publication.getIsbn()));
            publications.add(obj);
            notifyObservers(obj);
            return publicationDao.save(publications);
        }
    }

    @Override
    public boolean addObserver(Subscriber subscriber) {
        for (Subscriber sub : subscribers) {
            if (sub.getName().equals(subscriber.getName())) {
                return false;
            }
        }
        subscriber.setNumberOfPublications(countNumberOfPublications(subscriber));
        subscribers.add(subscriber);
        writeObserversToFile();
        return true;
    }

    public boolean isRoundNumberOfSubscribers() {
        return (subscribers.size() + 1) % 5 == 0;
    }

    @Override
    public boolean deleteObserver(Subscriber subscriber) {
        boolean result = subscribers.remove(subscriber);
        subscriber.setNumberOfPublications(countNumberOfPublications(subscriber));
        writeObserversToFile();
        return result;
    }

    @Override
    public void notifyObservers(Publication publication) {
        for (Subscriber observer : subscribers) {
            observer.handleEvent(publication);
        }
    }

    private List<Subscriber> readObserversFromFile() {
        List<Subscriber> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/main/resources/files/observers.txt"))) {
            list = (ArrayList<Subscriber>) ois.readObject();
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return list;
    }

    private void writeObserversToFile() {
        try (FileOutputStream file = new FileOutputStream(new File(Constants.OBSERVERS_FILE))) {
           ObjectOutputStream objectOutputStream = new ObjectOutputStream(file);
            objectOutputStream.writeObject(subscribers);
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
    }

    public Optional<Subscriber> findByName(String name) {
        Optional<Subscriber> subscriberOptional = Optional.empty();
        for (Subscriber subscriber : subscribers) {
            if (subscriber.getName().equals(name)) {
                subscriberOptional = Optional.of(subscriber);
            }
        }
        return subscriberOptional;
    }

    public Integer countNumberOfPublications(Subscriber subscriber) {
        int count = 0;
        for (Publication publication : publications) {
            PublicationType type;
            if (publication.getClass().toString().contains("Book")) {
                type = PublicationType.BOOK;
            } else {
                type = PublicationType.MAGAZINE;
            }
            if (type == subscriber.getPublicationType()
                    && publication.getYearOfPublishing().equals(subscriber.getYearOfPublishing())) {
                count++;
            }
        }
        return count;
    }
}
