package unima.bmvidatarun.truckoo.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import unima.bmvidatarun.truckoo.model.WeeklyLog;

/**
 * Created by Mukizen on 02.12.2016.
 */

public class LogStorage {

    private final static String NAME    = "spLogStorage";
    private final static String LOG_KEY = "logKey";

    public static WeeklyLog retrieveWeeklyLog(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(LOG_KEY, "");
        return gson.fromJson(json, WeeklyLog.class);
    }

    public static void storeWeeklyLog(Context context, WeeklyLog weeklyLog) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(weeklyLog);
        editor.putString(LOG_KEY, json);
        editor.apply();
    }

}
