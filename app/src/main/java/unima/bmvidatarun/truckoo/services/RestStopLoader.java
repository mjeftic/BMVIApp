package unima.bmvidatarun.truckoo.services;

import rx.Observable;
import unima.bmvidatarun.truckoo.rest.ServiceFactory;

/**
 * Created by Mukizen on 03.12.2016.
 */

public class RestStopLoader {

    public static Observable loadRestStop(double longitude, double latitude, int range) {
        return ServiceFactory.buildRestStopService().findRestStops(longitude, latitude, range);
    }

    //FIXME
    public static Observable calculateRoute(double startLatitude, double startLongitude, double endLatitude, double endLongitude, int interval) {
        return ServiceFactory.buildGeoService().calculateRoute(startLongitude,startLatitude,endLatitude,endLongitude,interval, "[{'name':'toilet'}]");
    }

}
