package unima.bmvidatarun.truckoo.model;

/**
 * Created by Marko on 03.12.16.
 */
public class SignificantPoint {
    double   latitude;
    double   longitude;
    RestStop restStop;

    public SignificantPoint(double latitude, double longitude, RestStop restStop) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.restStop = restStop;
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

    public RestStop getRestStop() {
        return restStop;
    }

    public void setRestStop(RestStop restStop) {
        this.restStop = restStop;
    }
}
