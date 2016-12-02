package unima.bmvidatarun.truckoo.rest;

import android.content.Context;
import android.support.annotation.NonNull;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author Daniel Holderbaum
 */
public class ServiceFactory {

    public static RestStopService buildRestStopService() {
        Retrofit retrofit = buildRetrofit();
        return retrofit.create(RestStopService.class);

    }

    @NonNull
    private static Retrofit buildRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://84.200.109.59:2016");
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        return builder.build();
    }

}
