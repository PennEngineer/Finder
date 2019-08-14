import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the user input and generate the proper output string
 */
public class InputProcessor {
    private boolean zipIsValid;
    private String outputString;
    private static String zipCode;
    private static String temp;

    /**
     * This constructor contains the logic to set the correct output string based on the user inputs
     *
     * @param zipInput  the zipcode input from the user in the original string form
     * @param venueType the venue type input from the user
     */

    public InputProcessor(String zipInput, String venueType) {
        DataProcessor processor = new DataProcessor();
        boolean isValidNumber = zipInput.matches("\\d{5}");
        List<Venue> venues = new ArrayList<>();
        boolean zipExists = false;
        if (isValidNumber) {
            int zipInt = Integer.parseInt(zipInput);
            zipExists = processor.zipCodeExists(zipInt);
            if (zipExists) {
                venues.addAll(processor.getRecommendations(venueType, zipInt));

                temp = WeatherService.getTemperature(zipInput);
                zipCode = zipInput;
            }
        }

        this.outputString = getOutputString(isValidNumber, zipExists, venues);
        this.zipIsValid = isValidNumber && zipExists;
    }


    /**
     * Getter
     *
     * @return was the string zipcode input valid
     */
    public boolean isZipValid() {
        return zipIsValid;
    }

    /**
     * Getter
     *
     * @return get generated output string
     */
    public String getOutputString() {
        return outputString;
    }

    /**
     * Private static method to be used in the constructor to generate the correct output string based on inputs
     *
     * @param isValidNumber boolean, was the zipcode in the proper format (ie 5 digits)
     * @param zipExists     boolean, does the zipcode exist in our database
     * @param venues        List of venues
     * @return the output string to be displayed in the front-end
     */
    private static String getOutputString(boolean isValidNumber, boolean zipExists, List<Venue> venues) {
        if (!isValidNumber) {
            return getInvalidZipString();
        }
        if (!zipExists) {
            return getZipDoesntExistString();
        }

        if (venues.size() == 0) {
            return "There are 0 search results nearby that fit your searched venue preference.";
        }

        StringBuilder returnString = new StringBuilder("The temperature at " + zipCode + " is " + temp + " F\n\n" + "Number of search results nearby: " + venues.size() + "\n\n");
        venues.forEach(r -> {
                    returnString.append(r);
                    returnString.append("\n\n");
                }
        );
        return returnString.toString();
    }

    /**
     * @return String indicating the entered zip code was invalid
     */
    private static String getInvalidZipString() {
        return "The zipcode you inputted isn't in the right format, please enter a 5 digit US zipcode.";
    }

    /**
     * @return String indicating the entered zip code doesn't exist in our database
     */
    private static String getZipDoesntExistString() {
        return "We don't have this zipcode in our database!  Please try a different one.";
    }
}
