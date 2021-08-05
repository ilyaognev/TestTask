import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxParseToList extends DefaultHandler {
    private StringBuilder currentValue = new StringBuilder();
    List<Item> result;
    Item currentItem;

    public List<Item> getResult() {
        return result;
    }

    @Override
    public void startDocument() {
        result = new ArrayList<>();
    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes) {

        if (qName.equalsIgnoreCase("item")) {
            currentItem = new Item();

            currentItem.setCity(attributes.getValue("city"));
            currentItem.setStreet(attributes.getValue("street"));
            currentItem.setHouse(attributes.getValue("house"));
            currentItem.setFloor(Integer.parseInt(attributes.getValue("floor")));

            result.add(currentItem);
        }
    }
}
