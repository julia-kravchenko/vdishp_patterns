package by.bsuir.patternslab.service.abstractfactory;

import by.bsuir.patternslab.entity.Publication;
import by.bsuir.patternslab.utils.Constants;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public abstract class XMLFactory {
    protected String resource;

    XMLFactory(String resource) {
        this.resource = resource;
    }

    public ArrayList<Publication> parseProducts(String element) throws ParserConfigurationException, SAXException, IOException {
        XMLParser xmlParser;
        switch (element) {
            case Constants.BOOK: {
                xmlParser = createXMLParser(Constants.BOOK);
                return xmlParser.parseProducts();
            }
            case Constants.MAGAZINE: {
                xmlParser = createXMLParser(Constants.MAGAZINE);
                return xmlParser.parseProducts();
            }
        }
        return null;
    }

    protected abstract XMLParser createXMLParser(String element) throws IOException, SAXException, ParserConfigurationException;

    public abstract String getType();
}
