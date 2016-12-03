package unima.bmvidatarun.truckoo.view.home;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import unima.bmvidatarun.R;
import unima.bmvidatarun.truckoo.model.Route;
import unima.bmvidatarun.truckoo.persistence.TargetStorage;
import unima.bmvidatarun.truckoo.rest.ServiceFactory;
import unima.bmvidatarun.truckoo.util.PlacesAutoCompleteAdapter;
import unima.bmvidatarun.truckoo.view.time.TimeActivity;

/**
 * Created by Mukizen on 02.12.2016.
 */

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.suggestion_view)           AutoCompleteTextView autoCompleteTextView;
    @BindView(R.id.transportation_check_icon) ImageView            transportChecked;
    @BindView(R.id.restaurant_check_icon)     ImageView            restaurantChecked;
    @BindView(R.id.toilets_check_icon)        ImageView            toiletsChecked;
    @BindView(R.id.shower_check_icon)         ImageView            showerChecked;
    @BindView(R.id.plan_button)               Button               planButton;

    Subscription datasubscriber;


    private GoogleApiClient           googleApiClient;
    private AutocompleteFilter        filter;
    private LatLngBounds              bounds;
    private PlacesAutoCompleteAdapter mAdapter;
    private Activity                  activity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setGoogleApiClient();
        ButterKnife.bind(this);
        initAdapter();
        autoCompleteTextView.setOnItemClickListener(mAutocompleteClickListener);
        autoCompleteTextView.setAdapter(mAdapter);
        activity = this;

    }


    @OnClick(R.id.restaurant_button)
    public void restaurantChecked(View view) {
        if (restaurantChecked.getVisibility() == View.VISIBLE) {
            restaurantChecked.setVisibility(View.GONE);
            restaurantChecked.animate().alpha(0.0f);
        } else {
            restaurantChecked.animate().alpha(1.0f);
            restaurantChecked.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.shower_button)
    public void showerChecked(View view) {
        if (showerChecked.getVisibility() == View.VISIBLE) {
            showerChecked.setVisibility(View.GONE);
            showerChecked.animate().alpha(0.0f);
        } else {
            showerChecked.animate().alpha(1.0f);
            showerChecked.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.toilette_button)
    public void toiletteChecked(View view) {
        if (toiletsChecked.getVisibility() == View.VISIBLE) {
            toiletsChecked.animate().alpha(0.0f);
            toiletsChecked.setVisibility(View.GONE);
        } else {
            toiletsChecked.animate().alpha(1.0f);
            toiletsChecked.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.fuel_station_button)
    public void fuelStationChecked(View view) {
        if (transportChecked.getVisibility() == View.VISIBLE) {
            transportChecked.animate().alpha(0.0f);
            transportChecked.setVisibility(View.GONE);
        } else {
            transportChecked.animate().alpha(1.0f);
            transportChecked.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.plan_button)
    public void planClicked(final View view) {
        datasubscriber = ServiceFactory.buildGeoService().calculateRoute(48.939685588000032, 12.647677558000055, 11.582104483000023,
                                                                         48.508754617000079, 10, "[{\"name\":\"toilet\"}]").subscribeOn(
                Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Route>() {
            @Override
            public void onCompleted() {
                Intent intent = new Intent(getApplication(), TimeActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, planButton, "transition");
                startActivity(intent, options.toBundle());

            }

            @Override
            public void onError(Throwable e) {
                Log.d("Google", e.getMessage());
            }

            @Override
            public void onNext(Route route) {
                Log.d("Google", String.valueOf(route.getTotalKilometers()));
            }
        });
    }

    public void setGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(getApplicationContext()).addApi(Places.GEO_DATA_API).build();
        googleApiClient.connect();
    }

    public void initAdapter() {
        //FIXME hardcoded
        Location locationForGermanyBounds = new Location("");
        locationForGermanyBounds.setLatitude(52.529341);
        locationForGermanyBounds.setLongitude(13.372691);
        bounds = locationToBounds(locationForGermanyBounds, 4);
        filter = new AutocompleteFilter.Builder().setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS).build();
        mAdapter = new PlacesAutoCompleteAdapter(getApplicationContext(), googleApiClient, bounds, filter);
    }

    private LatLngBounds locationToBounds(Location location, double radius) {
        //We need the bounds to get better google address suggestion results based on current user location
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        LatLng loc1 = new LatLng(latitude - radius, longitude - radius);
        LatLng loc2 = new LatLng(latitude + radius, longitude + radius);

        LatLngBounds latLngBounds = new LatLngBounds(loc1, loc2);
        return latLngBounds;

    }

    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a AutocompletePrediction from which we
             read the place ID and title.
              */
            final AutocompletePrediction item = mAdapter.getItem(position);
            final String placeId = item.getPlaceId();
            final CharSequence primaryText = item.getPrimaryText(null);

            Log.d("Google", "Autocomplete item selected: " + primaryText);
            /*^
             Issue a request to the Places Geo Data API to retrieve a Place object with additional
             details about the place.
              */
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi.getPlaceById(googleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);


        }
    };

    /**
     * Callback for results from a Places Geo Data API query that shows the first place result in
     * the details view on screen.
     */
    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                // Request did not complete successfully
                places.release();
                return;
            }
            // Get the Place object from the buffer.
            final Place place = places.get(0);
            setGoogleAddressData(place);

            places.release();
        }
    };


    private void setGoogleAddressData(Place place) {
        // Get the Place object from the buffer.
        double latitude = place.getLatLng().latitude;
        double longitude = place.getLatLng().longitude;

        Log.d("Google", "Latitude Longitude" + latitude + " " + longitude);

        Location target = new Location("");
        target.setLatitude(latitude);
        target.setLongitude(longitude);

        TargetStorage.storeTarget(getApplicationContext(), target);

        Geocoder geocoder = new Geocoder(getApplicationContext(), place.getLocale());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
               /* mAdminArea = addresses.get(0).getAdminArea();
                mSubAdminArea = addresses.get(0).getSubAdminArea();
                mLocality = addresses.get(0).getLocality();
                mSubLocality = addresses.get(0).getSubLocality();
                mThoroughfare = addresses.get(0).getThoroughfare();
                mSubThoroughfare = addresses.get(0).getSubThoroughfare();
                mPostalCode = addresses.get(0).getPostalCode();
                mCountryName = addresses.get(0).getCountryName();*/

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
