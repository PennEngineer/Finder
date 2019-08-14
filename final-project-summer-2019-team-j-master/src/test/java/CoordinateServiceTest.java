import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinateServiceTest {
    @Test
    public void getCoordinatesTest(){
        CoordinateService service = new CoordinateService("us-zip-code-latitude-and-longitude.csv");

        Coordinates actualCoordinates = service.getCoordinates(84660);

        Coordinates expectedCoordintates = new Coordinates(40.10637, -111.65408);

        assertEquals(expectedCoordintates, actualCoordinates);

    }

    @Test
    public void zidCodeExistsTest(){
        CoordinateService service = new CoordinateService("us-zip-code-latitude-and-longitude.csv");

        boolean goodZipResult = service.zipCodeExists(84321);

        assertTrue(goodZipResult);
    }

    @Test
    public void zipCodeDoesntExistTest(){
        CoordinateService service = new CoordinateService("us-zip-code-latitude-and-longitude.csv");

        boolean badZipResult = service.zipCodeExists(1234555);

        assertFalse(badZipResult);
    }
}
