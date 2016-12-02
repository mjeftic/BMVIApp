package unima.bmvidatarun.truckoo.view.home;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import unima.bmvidatarun.R;
import unima.bmvidatarun.truckoo.model.WeeklyLog;
import unima.bmvidatarun.truckoo.persistence.LogStorage;

/**
 * Created by Mukizen on 02.12.2016.
 */

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        WeeklyLog weeklyLog = LogStorage.retrieveWeeklyLog(this);
        if (weeklyLog == null) {
            weeklyLog = new WeeklyLog();
            LogStorage.storeWeeklyLog(this, weeklyLog);
        }

    }


}
