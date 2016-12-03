package unima.bmvidatarun.truckoo.model;

import java.util.List;

import unima.bmvidatarun.truckoo.util.Util;

/**
 * Created by Marko on 03.12.16.
 */

public class Route {

    double                 totalKilometers;
    double                 totalTravelTimeInMin;
    List<SignificantPoint> points;

    public Route(double totalKilometers, double totalTravelTimeInMin, List<SignificantPoint> points) {
        this.totalKilometers = totalKilometers;
        this.totalTravelTimeInMin = totalTravelTimeInMin;
        this.points = points;
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

    public SignificantPoint getNearestSignificantPoint(double latitude, double longitude) {
        double shortestDistance = Double.MAX_VALUE;
        SignificantPoint shortestPoint = null;

        for(SignificantPoint point : points) {
            double pointDistance = Util.distance(latitude,longitude,point.getLatitude(),point.getLongitude());
            if(shortestDistance > pointDistance) {
                shortestDistance = pointDistance;
                shortestPoint = point;
            }
        }
        return shortestPoint;
    }
}
