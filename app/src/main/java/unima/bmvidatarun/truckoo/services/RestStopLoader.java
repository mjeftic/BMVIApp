package unima.bmvidatarun.truckoo.services;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import unima.bmvidatarun.truckoo.model.RestStop;
import unima.bmvidatarun.truckoo.rest.RestStopService;
import unima.bmvidatarun.truckoo.rest.ServiceFactory;

/**
 * Created by Mukizen on 03.12.2016.
 */

public class RestStopLoader {

    public static Observable loadRestStop(double longitude, double latitude, int range) {
        return ServiceFactory.buildRestStopService().findRestStops(longitude, latitude, range);
    }

}
