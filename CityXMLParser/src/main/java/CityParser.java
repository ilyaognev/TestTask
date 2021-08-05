public class CityParser {
    public static void main(String[] args) {
        ItemListHandler handler = new ItemListHandler();
        handler.getListItems().forEach(System.out::println);
    }
}
