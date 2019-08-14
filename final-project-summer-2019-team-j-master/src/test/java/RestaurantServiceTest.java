import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
public class RestaurantServiceTest {
	
	@Test
	public void getRestaurantsFromStringTest() {

		String response = "{\"meta\":{\"code\":200,\"requestId\":\"5d3686f6af35f30025a954ea\"},\"response\":{\"venues\":[{\"id\":\"49c79540f964a520af571fe3\",\"name\":\"Blue Spoon Coffee Co.\",\"location\":{\"address\":\"76 Chambers St\",\"crossStreet\":\"at Broadway\",\"lat\":40.714427584609766,\"lng\":-74.00685853301651,\"labeledLatLngs\":[{\"label\":\"display\",\"lat\":40.714427584609766,\"lng\":-74.00685853301651}],\"distance\":195,\"postalCode\":\"10007\",\"cc\":\"US\",\"city\":\"New York\",\"state\":\"NY\",\"country\":\"United States\",\"formattedAddress\":[\"76 Chambers St (at Broadway)\",\"New York, NY 10007\",\"United States\"]},\"categories\":[{\"id\":\"4bf58dd8d48988d1e0931735\",\"name\":\"Coffee Shop\",\"pluralName\":\"Coffee Shops\",\"shortName\":\"Coffee Shop\",\"icon\":{\"prefix\":\"https:\\/\\/ss3.4sqi.net\\/img\\/categories_v2\\/food\\/coffeeshop_\",\"suffix\":\".png\"},\"primary\":true}],\"referralId\":\"v-1563854582\",\"hasPerk\":false},{\"id\":\"583af456ce593d41644bd7a6\",\"name\":\"Kaigo Coffee Room\",\"location\":{\"address\":\"120 Lafayette St Frnt C\",\"lat\":40.71863256946489,\"lng\":-74.00036738038041,\"labeledLatLngs\":[{\"label\":\"display\",\"lat\":40.71863256946489,\"lng\":-74.00036738038041}],\"distance\":804,\"postalCode\":\"10013\",\"cc\":\"US\",\"city\":\"New York\",\"state\":\"NY\",\"country\":\"United States\",\"formattedAddress\":[\"120 Lafayette St Frnt C\",\"New York, NY 10013\",\"United States\"]},\"categories\":[{\"id\":\"4bf58dd8d48988d1e0931735\",\"name\":\"Coffee Shop\",\"pluralName\":\"Coffee Shops\",\"shortName\":\"Coffee Shop\",\"icon\":{\"prefix\":\"https:\\/\\/ss3.4sqi.net\\/img\\/categories_v2\\/food\\/coffeeshop_\",\"suffix\":\".png\"},\"primary\":true}],\"referralId\":\"v-1563854582\",\"hasPerk\":false}]}}";
		
		List<Venue> expected = new ArrayList<>();
		
		expected.add(new Venue("coffee", "Blue Spoon Coffee Co.", 195, "[\"76 Chambers St (at Broadway)\",\"New York, NY 10007\",\"United States\"]"));
		expected.add(new Venue("coffee","Kaigo Coffee Room", 804, "[\"120 Lafayette St Frnt C\",\"New York, NY 10013\",\"United States\"]"));
		
		assertEquals(expected, VenueService.getRestaurantsFromString(response, "coffee"));
		
	}	

		@Test
		public void getResponseStringTest() {
			Coordinates c = new Coordinates(19.4326,99.1332);
			String results = VenueService.getResponseString(c, "sushi").split("response")[1];
			String results2 = results.split("referralId")[0];
			
			assertEquals(results2,"\":{\"venues\":[{\"id\":\"58e9f130e309e104fbc0ce72\",\"name\":\"โฮชิ ซูชิ Hoshi Sushi\",\"location\":{\"lat\":19.369067,\"lng\":99.202929,\"labeledLatLngs\":[{\"label\":\"display\",\"lat\":19.369067,\"lng\":99.202929}],\"distance\":10179,\"postalCode\":\"50190\",\"cc\":\"TH\",\"city\":\"Phrao\",\"state\":\"เชียงใหม่\",\"country\":\"ประเทศไทย\",\"formattedAddress\":[\"Phrao\",\"เชียงใหม่ 50190\",\"ประเทศไทย\"]},\"categories\":[{\"id\":\"4bf58dd8d48988d111941735\",\"name\":\"Japanese Restaurant\",\"pluralName\":\"Japanese Restaurants\",\"shortName\":\"Japanese\",\"icon\":{\"prefix\":\"https:\\/\\/ss3.4sqi.net\\/img\\/categories_v2\\/food\\/japanese_\",\"suffix\":\".png\"},\"primary\":true}],\"");
		}


}
