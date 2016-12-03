package unima.bmvidatarun.truckoo.model;

import java.util.List;

/**
 * Created by Marko on 03.12.16.
 */

public class Route {

    private double                 totalKilometers;
    private double                 totalTravelTimeInMin;
    private List<SignificantPoint> points;
    private GeoLocation lastPoint;

    public Route(double totalKilometers, double totalTravelTimeInMin, List<SignificantPoint> points, GeoLocation lastPoint) {
        this.totalKilometers = totalKilometers;
        this.totalTravelTimeInMin = totalTravelTimeInMin;
        this.points = points;
        this.lastPoint = lastPoint;
    }

    public double getTotalKilometers() {
        return totalKilometers;
    }

    public void setTotalKilometers(double totalKilometers) {
        this.totalKilometers = totalKilometers;
    }

    public double getTotalTravelTimeInMin() {
        return totalTravelTimeInMin;
    }

    public void setTotalTravelTimeInMin(double totalTravelTimeInMin) {
        this.totalTravelTimeInMin = totalTravelTimeInMin;
    }

    public List<SignificantPoint> getPoints() {
        return points;
    }

    public void setPoints(List<SignificantPoint> points) {
        this.points = points;
    }

    public GeoLocation getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(GeoLocation lastPoint) {
        this.lastPoint = lastPoint;
    }
}
