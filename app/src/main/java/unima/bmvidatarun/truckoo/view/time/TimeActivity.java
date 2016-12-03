package unima.bmvidatarun.truckoo.view.time;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import unima.bmvidatarun.R;
import unima.bmvidatarun.truckoo.model.DailyLog;
import unima.bmvidatarun.truckoo.model.GeoLocation;
import unima.bmvidatarun.truckoo.model.Route;
import unima.bmvidatarun.truckoo.model.WeeklyLog;
import unima.bmvidatarun.truckoo.persistence.RouteStorage;
import unima.bmvidatarun.truckoo.services.NotificationService;

/**
 * Created by Marko on 02.12.16.
 */

public class TimeActivity extends AppCompatActivity {

    private Timer     mTimer1;
    private TimerTask mTt1;
    private Handler mTimerHandler = new Handler();

    private WeeklyLog weeklyLog;
    private DailyLog  currentDailyLog;
    private int       position;
    private Activity  activity;
    private Route     route;

    @BindView(R.id.toolbar) Toolbar  toolbar;
    @BindView(R.id.title)   TextView title;

    @BindView(R.id.hours)     TextView hoursCurrent;
    @BindView(R.id.hoursDay)  TextView hoursDay;
    @BindView(R.id.hoursWeek) TextView hoursWeek;

    @BindView(R.id.minutes)     TextView minutesCurrent;
    @BindView(R.id.minutesDay)  TextView minutesDay;
    @BindView(R.id.minutesWeek) TextView minutesWeek;

    @BindView(R.id.circle_progress_bar)      ProgressBar progressBarCurrent;
    @BindView(R.id.circle_progress_bar_day)  ProgressBar progressBarDay;
    @BindView(R.id.circle_progress_bar_week) ProgressBar progressBarWeek;

    @BindView(R.id.start_stop) Button  button;
    private                    boolean isRunning;

    @BindView(R.id.warningTriangle) ImageView warningTriangle;
    @BindView(R.id.divider)         TextView  divider;
    @BindView(R.id.totalTime)       TextView  totalTime;
    @BindView(R.id.record)          ImageView record;


    @BindView(R.id.target)   TextView targetTextView;
    @BindView(R.id.distance) TextView distanceTextView;
    @BindView(R.id.duration) TextView durationTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText(R.string.rest_time_assistent);
        title.setTextColor(Color.WHITE);

        route = RouteStorage.retrieveRoute(getApplicationContext());
        if (route != null) {
            distanceTextView.setText(String.valueOf(Math.round(route.getTotalKilometers())) + " km");
            durationTextView.setText(getStringDuration(route.getTotalTravelTimeInMin()));
            targetTextView.setText(getCityByLocation(route.getLastPoint()));
        }


        isRunning = false;
        weeklyLog = new WeeklyLog();
        position = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        currentDailyLog = weeklyLog.getDailyLogs().get(position);
        weeklyLog.getDailyLogs().remove(position);
        weeklyLog.fillOtherWeekDays();
        currentDailyLog.mockDrivenTime();
        this.activity = this;
        update();
    }

    private String getCityByLocation(GeoLocation location) {

        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this);
            if (location.getLatitude() != 0 || location.getLongitude() != 0) {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                String city = addresses.get(0).getAddressLine(1);
                return city;

            } else {
                Toast.makeText(this, "latitude and longitude are null", Toast.LENGTH_LONG).show();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overview_menu, menu);//Menu Resource, Menu
        return super.onCreateOptionsMenu(menu);
    }

    @OnClick(R.id.start_stop)
    protected void startStopPressed(View view) {
        if (isRunning) {
            isRunning = false;
            stopTimer();
            button.setText(R.string.start_driving);
            button.setBackgroundColor(getColor(R.color.greenColor));

            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(
                    "http://maps.google.com/maps?saddr=" + "48.9396" + "," + "12.64767" + "&daddr=" + route.getLastPoint().getLatitude() + "," + route.getLastPoint().getLongitude()));
            startActivity(intent);
        } else {
            isRunning = true;

            startTimer();
            button.setText(R.string.stop_driving);
            button.setBackgroundColor(getColor(R.color.colorAccent));

        }

    }

    private void stopTimer() {
        record.clearAnimation();
        record.setVisibility(View.GONE);
        if (mTimer1 != null) {
            mTimer1.cancel();
            mTimer1.purge();
        }
    }

    private void startTimer() {
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.blinking);
        record.setVisibility(View.VISIBLE);
        record.startAnimation(myFadeInAnimation);
        mTimer1 = new Timer();
        mTt1 = new TimerTask() {
            public void run() {
                mTimerHandler.post(new Runnable() {
                    public void run() {
                        currentDailyLog.addMinutes(15);
                        update();
                    }
                });
            }
        };

        mTimer1.schedule(mTt1, 1, 1000);
    }

    private void update() {


        if (currentDailyLog.getDrivenSinceLastPause() <= 270) {
            long currentHours = currentDailyLog.getDrivenSinceLastPause() / 60;
            long currentMinutes = currentDailyLog.getDrivenSinceLastPause() % 60;
            int percentage = (int) (((double) currentDailyLog.getDrivenSinceLastPause() / 270) * 100);

            if (percentage > 70) {
                progressBarCurrent.setProgressDrawable(getDrawable(R.drawable.progress_foreground_warning));
                warningTriangle.setVisibility(View.VISIBLE);
                divider.setTextColor(getColor(R.color.colorWarning));
                totalTime.setTextColor(getColor(R.color.colorWarning));
                NotificationService.getInstance().showNotification(route.getPoints().get(route.getPoints().size() - 1).getReststop());
            }
            hoursCurrent.setText(getDoubleDigitValue(currentHours));
            minutesCurrent.setText(getDoubleDigitValue(currentMinutes));
            progressBarCurrent.setProgress(percentage);
        }
        if (currentDailyLog.getDrivenToday() <= 540) {
            long dayHours = currentDailyLog.getDrivenToday() / 60;
            long dayMinutes = currentDailyLog.getDrivenToday() % 60;
            int dayPercentage = (int) (((double) currentDailyLog.getDrivenToday() / 540d) * 100);

            hoursDay.setText(getDoubleDigitValue(dayHours));
            minutesDay.setText(getDoubleDigitValue(dayMinutes));
            progressBarDay.setProgress(dayPercentage);
        }
        long weekSum = currentDailyLog.getDrivenToday();
        for (DailyLog dailyLog : weeklyLog.getDailyLogs()) {
            weekSum += dailyLog.getDrivenToday();
        }
        if (weekSum <= 3240) {
            long weekHours = weekSum / 60;
            long weekMinutes = weekSum % 60;
            int weekPercentage = (int) (((double) weekSum / 3240d) * 100);

            hoursWeek.setText(getDoubleDigitValue(weekHours));
            minutesWeek.setText(getDoubleDigitValue(weekMinutes));
            progressBarWeek.setProgress(weekPercentage);
        }
    }

    private String getDoubleDigitValue(long total) {
        DecimalFormat twoPlaces = new DecimalFormat("00");
        return twoPlaces.format(total);
    }

    private String getStringDuration(double minutes) {
        String duration;
        long hours = (int) minutes / 60;
        long min = (int) minutes % 60;
        return hours + " h " + min + " min";
    }


}
