import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This is a service class that, loads a .csv file into a map of zipcode to a Coordinates object, which holds
 * latitude and longitude values
 */
public class CoordinateService {

    private final Map<Integer, Coordinates> coordinatesMap;

    /**
     * Creates CoordinateService object
     *
     * @param coordinateFile the file which houses the mappings of zipcode to lat/lon values
     */
    public CoordinateService(String coordinateFile) {
        this.coordinatesMap = createCoordinatesMap(coordinateFile);
    }

    /**
     * Get the latitude and longitude coordinates for a given US zipcode
     *
     * @param zipCode 5 digit US zipcode
     * @return the lat/lon coordinates of the given zip code.
     */
    public Coordinates getCoordinates(int zipCode) {
        return coordinatesMap.get(zipCode);  //(0.0, 0.0) is a place holder I'm guessing (CN)?
    }

    /**
     * Checks if the map has the requested zipcode
     *
     * @param zipCode the zipcode to check
     * @return a boolean whether or not the map has the requested zip
     */
    public boolean zipCodeExists(int zipCode) {
        return coordinatesMap.containsKey(zipCode);
    }

    private static Map<Integer, Coordinates> createCoordinatesMap(String fileName) {
        Map<Integer, Coordinates> map = new HashMap<>();
        try {
            map = Files.lines(Paths.get(fileName))
                    .skip(1)
                    .map(l -> l.split(";"))
                    .collect(Collectors.toMap((s) -> Integer.parseInt(s[0]), (String[] s) -> new Coordinates(Double.valueOf(s[3]), Double.valueOf(s[4]))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}
