package by.bsuir.patternslab.dao;

import by.bsuir.patternslab.entity.Publication;
import by.bsuir.patternslab.entity.comparison.PublicationPublishingHouseComparator;
import by.bsuir.patternslab.entity.comparison.PublicationTitleComparator;
import by.bsuir.patternslab.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class PublicationDaoImpl implements Dao<Publication> {
    private static final PublicationDaoImpl instance = new PublicationDaoImpl();
    private static final Logger logger = LogManager.getLogger(PublicationDaoImpl.class);

    private PublicationDaoImpl() {

    }

    public static PublicationDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean save(Set<Publication> publications) {
        return writeToFile(publications);
    }

    @Override
    public Set<Publication> load() {
        Comparator<Publication> comparing = new PublicationTitleComparator().thenComparing(new PublicationPublishingHouseComparator());
        Set<Publication> publications = new TreeSet<>(comparing);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constants.FILE_PATH))) {
            publications = (TreeSet<Publication>) ois.readObject();
        } catch (Exception e) {
            logger.trace(e.getMessage());
        }
        return publications;
    }

    @Override
    public boolean delete(Set<Publication> publications) {
        return writeToFile(publications);
    }

    private boolean writeToFile(Set<Publication> publications) {
        try (FileOutputStream file = new FileOutputStream(new File(Constants.FILE_PATH))) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(file);
            objectOutputStream.writeObject(publications);
            return true;
        } catch (Exception e) {
            logger.trace(e.getMessage());
            return false;
        }
    }
}
