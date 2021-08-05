import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ItemListHandler {

    public List<Item> getListItems() {
        List<Item> items = new ArrayList<>();

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try (InputStream is = getXMLFileAsStream()) {

            SAXParser saxParser = factory.newSAXParser();
            SaxParseToList handler = new SaxParseToList();
            saxParser.parse(is, handler);

            items = handler.getResult();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    private static InputStream getXMLFileAsStream() {
        return CityParser.class.getClassLoader().getResourceAsStream("citiesBook.xml");
    }
}
