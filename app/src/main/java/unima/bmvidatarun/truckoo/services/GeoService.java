package unima.bmvidatarun.truckoo.services;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import unima.bmvidatarun.truckoo.model.Route;

/**
 * Created by Marko on 03.12.16.
 */

public interface GeoService {
    @GET("/route/{startlatitude}/{startlongitude}/{endlatitude}/{endlongitude}/{interval}/{filter}")
    Observable<Route> calculateRoute(@Path("startlongitude") double startlongitude, @Path("startlatitude") double startlatitude,
                                     @Path("endlatitude") double endlatitude, @Path("endlongitude") double endlongitude,
                                     @Path("interval") int interval, @Path("filter") String filter);

}
