import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemListHandlerTest {
    private static ItemListHandler testHandler;

    @BeforeAll
    static void setUp() {
        System.out.println("Set up test...");
        testHandler = new ItemListHandler();
        testHandler.setFileName("citiesTestBook.xml");
    }

    @Test
    void getListItems() {
        assertEquals(testHandler.getListItems().size(), 14);
    }

    @Test
    void findDuplicates() {
        Item item = testHandler.findDuplicates(testHandler.getListItems()).keySet().stream().findFirst().orElse(new Item());

        assertAll("Item{city='Барнаул', street='Дальняя улица', house='56', floor=2}=2",
                () -> assertEquals(item.getCity(), "Барнаул"),
                () -> assertEquals(item.getStreet(), "Дальняя улица"),
                () -> assertEquals(item.getHouse(), "56"),
                () -> assertEquals(item.getFloor(), 2),
                () -> assertEquals(testHandler.findDuplicates(testHandler.getListItems()).size(), 1)
        );
    }

    @Test
    void floorCityCounter() {
        String city = testHandler.floorCityCounter(testHandler.getListItems()).keySet().stream().findFirst().orElse(null);
        Integer floor = testHandler.floorCityCounter(testHandler.getListItems()).get(city).entrySet().stream().findFirst().orElseThrow(IllegalArgumentException::new).getKey();
        Long count = testHandler.floorCityCounter(testHandler.getListItems()).get(city).entrySet().stream().findFirst().orElseThrow(IllegalArgumentException::new).getValue();

        assertAll("Видное={2=1, 3=1}, Великий Новгород={1=1}, Азов={3=1}, СПБ={2=2, 3=2}, Балаково={2=1}, Братск={5=2}, Батайск={4=1}, Барнаул={2=2}",
                () -> assertEquals(city, "Видное"),
                () -> assertEquals(floor, 2),
                () -> assertEquals(count, 1),
                () -> assertEquals(testHandler.floorCityCounter(testHandler.getListItems()).size(), 8)
        );
    }
}