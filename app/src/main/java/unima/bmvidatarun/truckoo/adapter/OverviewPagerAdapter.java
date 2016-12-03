package unima.bmvidatarun.truckoo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

import unima.bmvidatarun.R;
import unima.bmvidatarun.truckoo.model.DailyLog;
import unima.bmvidatarun.truckoo.model.WeeklyLog;
import unima.bmvidatarun.truckoo.persistence.LogStorage;
import unima.bmvidatarun.truckoo.util.TimeItemInflater;

/**
 * Created by Mukizen on 03.12.2016.
 */

public class OverviewPagerAdapter extends PagerAdapter {

    private WeeklyLog      weeklyLog;
    private DailyLog       currentDailyLog;
    private Context        mContext;
    private LayoutInflater mLayoutInflater;

    public OverviewPagerAdapter(final Context context) {
        weeklyLog = LogStorage.retrieveWeeklyLog(context);
        currentDailyLog = weeklyLog.getDailyLogs().get(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;
        view = mLayoutInflater.inflate(R.layout.time_item, container, false);
        TextView hours = (TextView) view.findViewById(R.id.hours);
        TextView minutes = (TextView) view.findViewById(R.id.minutes);

        switch (position) {
            case 0:
                view.setId(R.id.current_time);
                break;
            case 1:
                view.setId(R.id.total_day);
                break;
            case 2:
                view.setId(R.id.total_week);
                break;

        }
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}
