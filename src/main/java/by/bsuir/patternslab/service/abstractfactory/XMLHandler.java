package by.bsuir.patternslab.service.abstractfactory;

import by.bsuir.patternslab.entity.Book;
import by.bsuir.patternslab.entity.Magazine;
import by.bsuir.patternslab.entity.Publication;
import by.bsuir.patternslab.entity.PublishingHouse;
import by.bsuir.patternslab.utils.Constants;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XMLHandler extends DefaultHandler implements XMLParser {
    private final ArrayList<Publication> products = new ArrayList<Publication>();
    private String elementValue;

    public XMLHandler() {
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        elementValue = new String(ch, start, length);
        elementValue = elementValue.replace("\n", "").trim();
    }

    @Override
    public ArrayList<Publication> parseProducts() {
        return products;
    }

    @Override
    public void startDocument() {
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attributes) {
        switch (qName) {
            case Constants.BOOK: {
                String title = attributes.getValue("title");
                String numberOfPages = attributes.getValue("numberOfPages");
                String publishingHouse = attributes.getValue("publishingHouse");
                String yearOfPublishing = attributes.getValue("yearOfPublishing");
                PublishingHouse publHouse = PublishingHouse.getByName(publishingHouse);
                String author = attributes.getValue("authors");
                List<String> authors = new ArrayList<>();
                authors.add(author);
                Book book = new Book(title, Integer.parseInt(numberOfPages), publHouse,
                        Integer.parseInt(yearOfPublishing), authors);
                products.add(book);

                break;
            }

            case Constants.MAGAZINE: {
                String title = attributes.getValue("title");
                String numberOfPages = attributes.getValue("numberOfPages");
                String publishingHouse = attributes.getValue("publishingHouse");
                String yearOfPublishing = attributes.getValue("yearOfPublishing");
                PublishingHouse publHouse = PublishingHouse.getByName(publishingHouse);
                String chiefEditor = attributes.getValue("chiefEditors");
                List<String> chiefEditors = new ArrayList<>();
                chiefEditors.add(chiefEditor);
                Magazine magazine = new Magazine(title, Integer.parseInt(numberOfPages), publHouse,
                        Integer.parseInt(yearOfPublishing), chiefEditors);
                products.add(magazine);

                break;
            }

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        try {
            switch (qName) {
                case Constants.BOOK:
                    getProducts();
                case Constants.MAGAZINE:
                    getProducts();

            }
        } catch (ClassCastException | NumberFormatException ignored) {
        }
    }

    /*private void addProduct(Attributes attributes) {
        String name = attributes.getValue("name");
        String type = attributes.getValue("type");
        String rate = attributes.getValue("rate");
        String minPeriod = attributes.getValue("minperiod");
        String maxPeriod = attributes.getValue("maxperiod");
        String maxSum = attributes.getValue("maxsum");
        String minSum = attributes.getValue("minsum");
        String currencyId = attributes.getValue("currencyid");
        Currency currency = new Currency(Long.parseLong(currencyId), "Белорусский рубль");

        CreditProduct product = new CreditProduct(name, type, Double.parseDouble(rate),
                Integer.parseInt(minPeriod), Integer.parseInt(maxPeriod),
                Double.parseDouble(minSum), Double.parseDouble(maxSum), currency);
        products.add(product);
    }*/

    private ArrayList<Publication> getProducts() {
        return products;
    }
}
