import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CityParser {
    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try (InputStream is = getXMLFileAsStream()) {

            SAXParser saxParser = factory.newSAXParser();
            SaxParseToList handler = new SaxParseToList();
            saxParser.parse(is, handler);

            // print all
            List<Item> result = handler.getResult();
            result.forEach(System.out::println);
            System.out.println("Как тут?");

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    // get XML file from resources folder.
    private static InputStream getXMLFileAsStream() {
        return CityParser.class.getClassLoader().getResourceAsStream("citiesBook.xml");
    }
}
