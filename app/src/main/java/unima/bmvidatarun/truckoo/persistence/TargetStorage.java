package unima.bmvidatarun.truckoo.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import unima.bmvidatarun.truckoo.model.Target;
import unima.bmvidatarun.truckoo.model.WeeklyLog;

/**
 * Created by Mukizen on 03.12.2016.
 */

public class TargetStorage {

    private final static String NAME       = "spTargetStorage";
    private final static String TARGET_KEY = "targetKey";

    public static Target retrieveTarget(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(TARGET_KEY, "");
        return gson.fromJson(json, Target.class);
    }

    public static void storeTarget(Context context, Target target) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(target);
        editor.putString(TARGET_KEY, json);
        editor.apply();
    }

}
