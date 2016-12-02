package unima.bmvidatarun.truckoo.model;

/**
 * Created by Mukizen on 02.12.2016.
 */

public class RestStop {

    private String restStopId;
    private String title;
    private String poiname;
    private String streetname;
    private double longitude;
    private double latitude;
    private double distance;

    public String getRestStopId() {
        return restStopId;
    }

    public void setRestStopId(String restStopId) {
        this.restStopId = restStopId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoiname() {
        return poiname;
    }

    public void setPoiname(String poiname) {
        this.poiname = poiname;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

}
