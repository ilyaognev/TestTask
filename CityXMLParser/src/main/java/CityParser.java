import java.util.List;

public class CityParser {
    public static void main(String[] args) {
        ItemListHandler handler = new ItemListHandler();
        List<Item> items = handler.getListItems();
        System.out.println("Number of duplicates in xml:");
        System.out.println(handler.findDuplicates(items));
        System.out.println();
        System.out.println("Count floor in the city: ");
        System.out.println(handler.floorCityCounter(items));
    }
}
