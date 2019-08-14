import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherService {
    /**
     * This method will get the weather JSON string from the OpenWeather api call
     *
     * @param zipCode
     * @return the api JSON string containing temperature
     */
    public static String getTemperature(String zipCode) {
        StringBuilder outputFromAPIFinal = new StringBuilder();
        URL userURL;
        try {
            //URL that will be use to call the API with authentication appkey
            userURL = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + Integer.parseInt(zipCode) + ",us&units=imperial&APPID=99434d01f25d335f1246f8c4521a0922");

            //opening the connection from the UC object
            URLConnection UC = userURL.openConnection();
            HttpURLConnection URLC = (HttpURLConnection) UC;
            //The connection is opened implicitly by calling getInputStream
            BufferedReader br = new BufferedReader(new InputStreamReader((URLC.getInputStream())));
            //output from API Call
            String outputFromAPI;

            while ((outputFromAPI = br.readLine()) != null) {
                outputFromAPIFinal.append(outputFromAPI);
            }
            br.close();
            //Thrown to indicate that a malformed URL has occurred. Either no legal protocol could be found in a specification string or the string could not be parsed.
        } catch (IOException e) {
            System.out.println("OpenWeather API call was not successful");
            e.printStackTrace();
        }
        return getTemperatureFromResponse(outputFromAPIFinal.toString());

    }

    /**
     * Regex method to be able to get the temperature from the JSON OpenWeather String
     *
     * @param output JSON string output from the api call
     * @return the temperature
     */
    private static String getTemperatureFromResponse(String output) {
        Pattern p = Pattern.compile("(?<=\"temp\":)..");
        Matcher m = p.matcher(output);
        String temp = "";
        while (m.find()) {
            temp = m.group();
        }
        return temp;
    }
}
