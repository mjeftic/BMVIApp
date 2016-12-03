package unima.bmvidatarun.truckoo.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Mukizen on 03.12.2016.
 */

public class LocationHelper {

    public static Location getCurrentLocation(Activity context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(context,
                                                  android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context,
                                                  android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        return location;
    }
}
