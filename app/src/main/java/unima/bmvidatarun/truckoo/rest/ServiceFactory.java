package unima.bmvidatarun.truckoo.rest;

import android.content.Context;
import android.support.annotation.NonNull;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;

/**
 * @author Daniel Holderbaum
 */
public class ServiceFactory {

    public static RestStopService buildRestStopService(Context context) {
        Retrofit retrofit = buildRetrofit(context);
        return retrofit.create(RestStopService.class);

    }

    @NonNull
    private static Retrofit buildRetrofit(Context context) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://84.200.109.59:2016");
        return builder.build();
    }

}
