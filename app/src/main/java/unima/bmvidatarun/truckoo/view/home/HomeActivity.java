package unima.bmvidatarun.truckoo.view.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.maps.model.LatLngBounds;

import unima.bmvidatarun.R;
import unima.bmvidatarun.truckoo.util.PlacesAutoCompleteAdapter;

/**
 * Created by Mukizen on 02.12.2016.
 */

public class HomeActivity extends AppCompatActivity {

    private GoogleApiClient           googleApiClient;
    private AutocompleteFilter        filter;
    private LatLngBounds              bounds;
    private Context                   ctx;
    private PlacesAutoCompleteAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }


}
