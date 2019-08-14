import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestaurantTest {

    @Test
    public void convertToMilesTest(){
        Venue restaurant = new Venue("Sushi", "My Sushi Restaurant", 100,"" );
        assertEquals(0.0621371, restaurant.getDistance());

    }

    @Test
    public void restaurantToStringTest(){
        Venue restaurant = new Venue("Sushi", "My Sushi Restaurant", 100,"[\"886 N 600 E\",\"Spanish Fork, UT 84660\",\"United States\"]" );
        String expectedString = "Name :     My Sushi Restaurant\n" +
                "Address:  886 N 600 E \n" +
                "                   Spanish Fork, UT 84660 \n" +
                "Distance: 0.06 Miles\n" +
                "Category: Sushi";
        String actualString = restaurant.toString();

        assertEquals(expectedString, actualString);
    }
}
