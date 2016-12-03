package unima.bmvidatarun.truckoo.view.warning;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import unima.bmvidatarun.R;

/**
 * Created by Mukizen on 03.12.2016.
 */

public class WarningActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar  toolbar;
    @BindView(R.id.title)   TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText(R.string.lb_rest_validation);
        title.setTextColor(Color.WHITE);
    }
}
