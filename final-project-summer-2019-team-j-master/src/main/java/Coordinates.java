import java.util.Objects;

// Data class representing lat/lon coordinates
public class Coordinates {

	private final double latitude;
	private final double longitude;

	
	/**
	 * Constructor, takes in two arguments representing geographical latitude and longitude geographical data, respectively and 
	 * initializes them to the constant variable longitude and latitude.
	 * @param latitude; type double, represents the coordinate measurement in relation to the north and south of the Earth's equator. 
	 * @param longitude; type double, represents the coordinate angular distance, east or west, of the prime meridian. 
	 */
	public Coordinates(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	/**
	 * Getter, accesses the private variable latitude
	 * @return; latitude value
	 */
	public double getLatitude() {
		return this.latitude;
	}
	/**
	 * Getter, accesses the private variable longitude
	 * @return; longitude value
	 */
	public double getLongitude() {
		return this.longitude;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Coordinates that = (Coordinates) o;
		return Double.compare(that.longitude, longitude) == 0 &&
				Double.compare(that.latitude, latitude) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(longitude, latitude);
	}

	@Override
	public String toString() {
		return "Coordinates{" +
				"latitude=" + latitude +
				", longitude=" + longitude +
				'}';
	}
}
