package unima.bmvidatarun.truckoo.view.home;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import unima.bmvidatarun.R;
import unima.bmvidatarun.truckoo.model.RestStop;
import unima.bmvidatarun.truckoo.model.WeeklyLog;
import unima.bmvidatarun.truckoo.persistence.LogStorage;
import unima.bmvidatarun.truckoo.rest.RestStopService;
import unima.bmvidatarun.truckoo.rest.ServiceFactory;

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
        loadRestStops();

    }

    private void loadRestStops() {
        RestStopService restStopService = ServiceFactory.buildRestStopService(this);
        restStopService.findRestStops(49.2, 13, 50).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Subscriber<RestStop>() {
                    @Override
                    public void onCompleted() {
                        Log.d("Hi","onCompeted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Hi","onerror");

                    }

                    @Override
                    public void onNext(RestStop restStop) {
                        Log.d("Hi","onNExt");

                    }
                });
    }


}
