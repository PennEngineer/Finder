import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataProcessorTest {

    @Test
    public void getRecommendationsSortsTest() {
        DataProcessor processor = new DataProcessor();

        List<Venue> restaurants = processor.getRecommendations("Sushi", 84660);

        Venue actualClosestRestaurant = restaurants.get(0);
        Venue expectedClosestRestaurant = restaurants.
                stream()
                .min(Comparator.comparingDouble(Venue::getDistance))
                .get();

        assertEquals(expectedClosestRestaurant, actualClosestRestaurant);

        Venue actualFurthestRestaurant = restaurants.get(restaurants.size() - 1);
        Venue expectedFurthestRestaurant = restaurants.
                stream()
                .max(Comparator.comparingDouble(Venue::getDistance))
                .get();

        assertEquals(expectedFurthestRestaurant, actualFurthestRestaurant);
    }

    @Test
    public void zipCodeExistsTestBadZip() {
        DataProcessor processor = new DataProcessor();

        boolean badZipCode = processor.zipCodeExists(1234567);

        assertFalse(badZipCode);
    }

    @Test
    public void zipCodeExistsTestGoodZip() {
        DataProcessor processor = new DataProcessor();

        boolean goodZipCode = processor.zipCodeExists(10017);

        assertTrue(goodZipCode);

    }
}
