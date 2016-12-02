package unima.bmvidatarun.truckoo.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

import com.google.gson.Gson;

/**
 * Created by Mukizen on 03.12.2016.
 */

public class TargetStorage {

    private final static String NAME       = "spLocationStorage";
    private final static String LOCATION_KEY = "locationKey";

    public static Location retrieveTarget(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(LOCATION_KEY, "");
        return gson.fromJson(json, Location.class);
    }

    public static void storeTarget(Context context, Location target) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(target);
        editor.putString(LOCATION_KEY, json);
        editor.apply();
    }

}
