package unima.bmvidatarun.truckoo.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import unima.bmvidatarun.truckoo.model.Route;
import unima.bmvidatarun.truckoo.model.WeeklyLog;

/**
 * Created by Mukizen on 03.12.2016.
 */

public class RouteStorage {

    private final static String NAME    = "spRouteStorage";
    private final static String LOG_KEY = "routeStorage";

    public static Route retrieveRoute(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(LOG_KEY, "");
        return gson.fromJson(json, Route.class);
    }

    public static void storeRoute(Context context, Route route) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(route);
        editor.putString(LOG_KEY, json);
        editor.apply();
    }

}
