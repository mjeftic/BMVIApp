package unima.bmvidatarun.truckoo.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import unima.bmvidatarun.R;
import unima.bmvidatarun.truckoo.model.RestStop;
import unima.bmvidatarun.truckoo.util.TruckooApplication;
import unima.bmvidatarun.truckoo.view.time.TimeActivity;

/**
 * Created by bausch on 03.12.16.
 */

public class NotificationService {
    private static NotificationService instance = new NotificationService();

    double latitude = 49.26;
    double longitude = 10.5;

    Context context = TruckooApplication.get().getApplicationContext();

    Notification notification;

    public void showNotification(RestStop restStop) {


        Bitmap bm = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher),
                context.getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_width),
                context.getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_height),
                true);

        Intent intent = new Intent(context, TimeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 01, intent, PendingIntent.FLAG_NO_CREATE);


        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle(String.format("Nächster Halt: %s", restStop.getPoiname()));
        builder.setContentText("This is the text");
        builder.setSubText("Some sub text");
        builder.setNumber(101);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(bm);
        builder.setTicker("Fancy Notification");
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_HIGH);
        notification = builder.build();
        NotificationManager notificationManger =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManger.notify(01, notification);
    }

    public NotificationService() {

    }

    public static NotificationService getInstance() {
        return instance;
    }
}
