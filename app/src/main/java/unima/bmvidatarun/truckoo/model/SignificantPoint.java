package unima.bmvidatarun.truckoo.model;

/**
 * Created by Marko on 03.12.16.
 */
public class SignificantPoint {
    double   latitude;
    double   longitude;
    RestStop reststop;

    public SignificantPoint(double latitude, double longitude, RestStop reststop) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.reststop = reststop;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public RestStop getReststop() {
        return reststop;
    }

    public void setReststop(RestStop reststop) {
        this.reststop = reststop;
    }
}
