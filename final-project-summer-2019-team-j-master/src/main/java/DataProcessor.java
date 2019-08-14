import java.util.List;

/**
 * Main backend class that communicates to the frontend
 */
public class DataProcessor {
    private CoordinateService coordinateService;

    /**
     * Constuctor, instantiates a CoordinateService to be used to go from zipcode to Coordinates
     */
    public DataProcessor() {
        this.coordinateService = new CoordinateService("us-zip-code-latitude-and-longitude.csv");
    }

    /**

     *
     * @return; ArrayList containing restaurant data; food type, name of restaurant, and distance from geolocation.
     */

    /**
     * Method for retrieving the Restaurants close to the given zipcode and of the desired food type
     *
     * @param venueType the desired venue type ie 'Sushi'
     * @param zipCode 5 digit US zipcode
     * @return list of venues that match the desired venue type sorted by proximity to the zipcode
     */
    public List<Venue> getRecommendations(String venueType, int zipCode) {
        Coordinates coordinates = coordinateService.getCoordinates(zipCode);
        return VenueService.getRecommendations(coordinates, venueType);
    }

    /**
     * Does the requested zipcode exist in the database
     *
     * @param zipCode the requested zipcode
     * @return boolean does the zipcode exist in the database
     */
    public boolean zipCodeExists(int zipCode) {
        return coordinateService.zipCodeExists(zipCode);
    }
}
