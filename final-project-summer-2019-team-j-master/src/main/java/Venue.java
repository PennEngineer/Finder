import java.util.Objects;

// data class that hold the relevant restaurant data
public class Venue {
    private String venueType;
    private String name;
    private double distance;
    private String address;

    /**
     * Constructor; takes the arguments food type of type String, name of restaurant of type String, and the distance to restaurant
     * of type Int.  The arguments are initialized to the class constant variables of the same name.  The arguments are data points
     * obtained from the parsed json string from the FourSquare API.
     *
     * @param venueType; category type of the venue
     * @param name;      name of the restaurant
     * @param distance;  distance to the restaurant
     * @param address;   the address of the restaurant
     */
    public Venue(String venueType, String name, int distance, String address) {
        this.venueType = cleanCategory(venueType);
        this.name = name;
        this.distance = convertToMiles(distance);
        this.address = address;
    }

    /**
     * Getter; allows the constant variable venueType to be called by other classes.
     *
     * @return; venue category of venue.
     */
    public String getVenueType() {
        return venueType;
    }

    /**
     * Getter; allows the constant variable name to be called by other classes.
     *
     * @return; name of restaurant.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter; allows the constant variable distance to be called by other classes.
     *
     * @return; distance to the restaurant.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Getter; allows the constant variable distance to be called by other classes.
     *
     * @return; address of the restaurant.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Converts the distance values obtained from the Foursquare API output, which by default
     * is stated in meters.
     * @param distanceMeters distance in meters
     * @return converted distance value in measurement meters.
     */
    private static double convertToMiles(int distanceMeters) {
        return 0.000621371 * distanceMeters;
    }

    /**
     * Cleans the address string obtained from the Foursquare API output.  Removes characters
     * "\".'[]"
     *
     * @param address
     * @return a cleaner string with unwanted characters removed.
     */
    public String parseAddress(String address) {
        if (address == null || address.equals("")) return "";
        String rawString = address;
        String regex = "\".'[]";
        char[] chars = regex.toCharArray();
        for (char c : chars) {
            rawString = rawString.replace(c, ' ');
        }
        String[] splitAddress = rawString.split(",");
        String printAddress = "";
        if (splitAddress.length == 4) {
            printAddress = splitAddress[0] + "\n" + "                  " +
                    splitAddress[1] + "," + splitAddress[2];
        } else {
            for (int i = 0; i < splitAddress.length - 1; i++) {
                printAddress = printAddress + "," + splitAddress[i];
            }
        }
        return printAddress;
    }

    /**
     * Corrects the venue category word so that it is properly capitalized
     *
     * @param category
     * @return correctly capitalized venue category
     */
    public String cleanCategory(String category) {
        if (category.equals("")) return "";
        return category.substring(0, 1).toUpperCase() + category.substring(1);
    }

    @Override
    public String toString() {
        return "Name :     " + this.getName() + "\n" +
                "Address:" + parseAddress(this.getAddress()) + "\n" +
                "Distance: " + String.format("%.2f", this.getDistance()) + " Miles" + "\n" +
                "Category: " + this.getVenueType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venue that = (Venue) o;
        return distance == that.distance &&
                Objects.equals(venueType, that.venueType) &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(venueType, name, distance, address);
    }
}
