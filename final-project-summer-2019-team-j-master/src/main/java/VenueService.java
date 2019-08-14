import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//this class uses the foursquare api to return a list of Restaurants of the given food type that are close to the coordinates
public class VenueService {
    /**
     * Gets the list of recommended restaurants of type Restaurant, which contains multi-dimensional data points; venue type,
     * name of restaurant, and the distance from the initial specified location.
     *
     * @param coordinates the coordinates of the restaurant search
     * @param venueType the desired venue type
     * @return; list of restaurants of the desired venue type sorted by proximity to the given coordinates
     */
    public static List<Venue> getRecommendations(Coordinates coordinates, String venueType) {
        String responseString = getResponseString(coordinates, venueType);
        return getRestaurantsFromString(responseString, venueType);
    }

    static String getResponseString(Coordinates coordinates, String venueType) {
        //URL that will be use to call the API
        String outputFromAPIFinal = "";
        URL userURL;
        try {
            userURL = new URL("https://api.foursquare.com/v2/venues/search?client_id=RK5U0OIGKJ02I4ICEDK1R0AAITUAH30USGDMLI5GVIEFLLYT&client_secret=EQM3L4F5HA2DJNQCQ51MYDBKBGVG4TJ5XNK2NLJGU1UHU4SY&ll="
                    + coordinates.getLatitude() + "," + coordinates.getLongitude() + "&query="
                    + venueType + "&v=20190719");

            //opening the connection from the UC object
            URLConnection UC = userURL.openConnection();
            //The connection is opened implicitly by calling getInputStream
            BufferedReader br = new BufferedReader(new InputStreamReader((UC.getInputStream())));
            //output from API Call
            String outputFromAPI;

            while ((outputFromAPI = br.readLine()) != null) {
                outputFromAPIFinal += outputFromAPI;
            }
            br.close();
            //Thrown to indicate that a malformed URL has occurred. Either no legal protocol could be found in a specification string or the string could not be parsed.
        } catch (IOException e) {
            System.out.println("FourSquare API call was not successful");
            e.printStackTrace();
        }
        return outputFromAPIFinal;

    }

    static List<Venue> getRestaurantsFromString(String json, String venueType) {
        List<Venue> listOfVenues = new ArrayList<>();

        // get response portion of string
        String[] arr = json.split("\"response\":");

        // take out last '}'
        String response = arr[1].substring(0, arr[1].length() - 1);

        // create json object from response
        JsonObject obj = new JsonParser().parse(response).getAsJsonObject();

        // get array of venues
        JsonArray venues = (JsonArray) obj.get("venues");

        // for each venue get relevant restaurant information and add to list
        venues.forEach(v -> listOfVenues.add(getVenueFromJsonObject(v.getAsJsonObject(), venueType)));

        // sort list of restaurants by distance
        listOfVenues.sort(Comparator.comparingDouble(Venue::getDistance));

        return listOfVenues;
    }

    private static Venue getVenueFromJsonObject(JsonObject jsonVenue, String venueType) {

        // pull the name out of object
        String name = jsonVenue.get("name").getAsString();

        // create json object from location data
        JsonObject location = jsonVenue.get("location").getAsJsonObject();

        // from the location data pull dista
        int distance = location.get("distance").getAsInt();
        String address = location.get("formattedAddress").toString();

        return new Venue(venueType, name, distance, address);
    }
}
//
