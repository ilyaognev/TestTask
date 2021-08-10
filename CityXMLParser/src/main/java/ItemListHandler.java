import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ItemListHandler {
    public String fileName = "citiesBook.xml";

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Item> getListItems() {
        List<Item> items = new ArrayList<>();

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try (InputStream is = getXMLFileAsStream(fileName)) {

            SAXParser saxParser = factory.newSAXParser();
            SaxParseToList handler = new SaxParseToList();
            saxParser.parse(is, handler);

            items = handler.getResult();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    public Map<Item, Long> findDuplicates(List<Item> items) {
        return items.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(element -> element.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    //Map<city, Map<floor, count>>
    public Map<String, Map<Integer, Long>> floorCityCounter(List<Item> items) {
        return items.stream()
                .collect(Collectors.groupingBy(Item::getCity, Collectors.groupingBy(Item::getFloor, Collectors.counting())));
    }

    private static InputStream getXMLFileAsStream(String fileName) {
        return CityParser.class.getClassLoader().getResourceAsStream(fileName);
    }
}