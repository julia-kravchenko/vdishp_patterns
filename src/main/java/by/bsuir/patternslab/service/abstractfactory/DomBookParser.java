package by.bsuir.patternslab.service.abstractfactory;

import by.bsuir.patternslab.entity.Book;
import by.bsuir.patternslab.entity.Publication;
import by.bsuir.patternslab.entity.PublishingHouse;
import by.bsuir.patternslab.utils.Constants;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomBookParser extends DOMParser {
    public DomBookParser(String resource) throws ParserConfigurationException, IOException, SAXException {
        super(resource);
    }

    @Override
    public ArrayList<Publication> parseProducts() {
        if (document == null) {
            System.out.println("document == null");
            return null;
        }

        return getProducts();
    }

    private ArrayList<Publication> getProducts() {
        ArrayList<Publication> products = new ArrayList<Publication>();
        NodeList elements = document.getElementsByTagName(Constants.BOOK);

        for (int i = 0; i < elements.getLength(); i++) {
            NamedNodeMap attributes = elements.item(i).getAttributes();

            String title = attributes.getNamedItem("title").getNodeValue();
            String numberOfPages = attributes.getNamedItem("numberOfPages").getNodeValue();
            String publishingHouse = attributes.getNamedItem("publishingHouse").getNodeValue();
            String yearOfPublishing = attributes.getNamedItem("yearOfPublishing").getNodeValue();
            String author = attributes.getNamedItem("authors").getNodeValue();

            List<String> authors = new ArrayList<>();
            authors.add(author);
            PublishingHouse publHouse = PublishingHouse.getByName(publishingHouse);

            Book book = new Book(title, Integer.parseInt(numberOfPages), publHouse,
                    Integer.parseInt(yearOfPublishing), authors);
            products.add(book);
        }
        return products;
    }
}
