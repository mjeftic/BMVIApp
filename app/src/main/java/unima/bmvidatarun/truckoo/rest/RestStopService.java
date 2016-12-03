package unima.bmvidatarun.truckoo.rest;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import unima.bmvidatarun.truckoo.model.RestStop;

/**
 * Created by Mukizen on 02.12.2016.
 */

public interface RestStopService {

    @GET("/reststops/{longitude}/{latitude}/{range}")
    Observable<RestStop> findRestStops(@Path("longitude") double longitude, @Path("latitude") double latitude, @Path("range") int range);


}
