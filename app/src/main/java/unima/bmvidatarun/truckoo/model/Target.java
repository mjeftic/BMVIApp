package unima.bmvidatarun.truckoo.model;

/**
 * Created by Marko on 03.12.16.
 */

public class Target {
    private double latitude;
    private double longitude;

    public Target(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
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
}
