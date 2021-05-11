package by.bsuir.patternslab.service.abstractfactory;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXXmlFactory extends XMLFactory {

    public SAXXmlFactory(String resource) {
        super(resource);
    }

    @Override
    protected XMLParser createXMLParser(String element) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(resource), handler);
        return handler;
    }

    @Override
    public String getType() {
        return "SAX Parser";
    }
}