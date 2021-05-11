package by.bsuir.patternslab.service.abstractfactory;

import by.bsuir.patternslab.utils.Constants;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DomXmlFactory extends XMLFactory {
    public DomXmlFactory(String resource) {
        super(resource);
    }

    @Override
    protected XMLParser createXMLParser(String element) throws IOException, SAXException, ParserConfigurationException {
        switch (element) {
            case Constants.BOOK: {
                return new DomBookParser(resource);
            }
            case Constants.MAGAZINE: {
                return new DomMagazineParser(resource);
            }
        }
        return null;
    }

    @Override
    public String getType() {
        return "DOM Parser";
    }
}
