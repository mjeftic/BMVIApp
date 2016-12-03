package unima.bmvidatarun.truckoo.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by bausch on 03.12.16.
 */


public class TruckooApplication extends Application {
    private static Context mContext;

    public static Context get() {
        return mContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}