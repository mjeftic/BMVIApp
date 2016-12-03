package unima.bmvidatarun.truckoo.view.time;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import unima.bmvidatarun.R;
import unima.bmvidatarun.truckoo.adapter.OverviewPagerAdapter;
import unima.bmvidatarun.truckoo.model.DailyLog;
import unima.bmvidatarun.truckoo.model.WeeklyLog;
import unima.bmvidatarun.truckoo.persistence.LogStorage;

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

    @BindView(R.id.fabStartStop) FloatingActionButton floatingActionButton;

    private boolean isRunning;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText(R.string.rest_time_assistent);
        title.setTextColor(Color.WHITE);
        isRunning = false;
        weeklyLog = new WeeklyLog();
        position = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        currentDailyLog = weeklyLog.getDailyLogs().get(position);
        weeklyLog.getDailyLogs().remove(position);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overview_menu, menu);//Menu Resource, Menu
        return super.onCreateOptionsMenu(menu);
    }

    @OnClick(R.id.fabStartStop)
    protected void startStopPressed(View view) {
        if (isRunning) {
            isRunning = false;
            stopTimer();
            floatingActionButton.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        } else {
            isRunning = true;
            startTimer();
            floatingActionButton.setImageResource(R.drawable.ic_pause_black_24dp);
        }

    }

    private void stopTimer() {
        if (mTimer1 != null) {
            mTimer1.cancel();
            mTimer1.purge();
        }
    }

    private void startTimer() {
        mTimer1 = new Timer();
        mTt1 = new TimerTask() {
            public void run() {
                mTimerHandler.post(new Runnable() {
                    public void run() {
                        currentDailyLog.addOneMinute();
                        if (currentDailyLog.getDrivenSinceLastPause() <= 300) {
                            long currentHours = currentDailyLog.getDrivenSinceLastPause() / 60;
                            long currentMinutes = currentDailyLog.getDrivenSinceLastPause() % 60;
                            int percentage = (int) (((double) currentDailyLog.getDrivenSinceLastPause() / 300d) * 100);

                            hoursCurrent.setText(getDoubleDigitValue(currentHours));
                            minutesCurrent.setText(getDoubleDigitValue(currentMinutes));
                            progressBarCurrent.setProgress(percentage);
                        }
                        if (currentDailyLog.getDrivenToday() <= 540) {
                            long dayHours = currentDailyLog.getDrivenSinceLastPause() / 60;
                            long dayMinutes = currentDailyLog.getDrivenSinceLastPause() % 60;
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
                            long weekHours = currentDailyLog.getDrivenSinceLastPause() / 60;
                            long weekMinutes = currentDailyLog.getDrivenSinceLastPause() % 60;
                            int weekPercentage = (int) (((double) weekSum / 3240d) * 100);

                            hoursWeek.setText(getDoubleDigitValue(weekHours));
                            minutesWeek.setText(getDoubleDigitValue(weekMinutes));
                            progressBarWeek.setProgress(weekPercentage);
                        }
                    }
                });
            }
        };

        mTimer1.schedule(mTt1, 1, 1000);
    }


    private String getDoubleDigitValue(long total) {
        DecimalFormat twoPlaces = new DecimalFormat("00");
        return twoPlaces.format(total);
    }


}
