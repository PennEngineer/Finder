import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordinatesTest {

    @Test
    public void toStringTest() {
        Coordinates coordinates = new Coordinates(0.0d, 0.0d);

        String expected = "Coordinates{latitude=0.0, longitude=0.0}";
        String actual = coordinates.toString();

        assertEquals(expected, actual);
    }
}
