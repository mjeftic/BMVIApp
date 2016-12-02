package unima.bmvidatarun.truckoo.model;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Mukizen on 02.12.2016.
 */

public class WeeklyLog {

    private Date startDate;
    private Date endDate;
    private long totalDrivenTime;
    private List<DailyLog> dailyLogs = new ArrayList<>(7);

    public WeeklyLog() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        startDate = cal.getTime();
        insertWeekDays();
    }

    private void insertWeekDays() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        for (int i = 0; i < 7; i++) {
            cal.add(Calendar.DATE, i);
            dailyLogs.add(new DailyLog(cal.getTime()));
        }
    }

    public long getTotalDrivenTime() {
        return totalDrivenTime;
    }

    public void setTotalDrivenTime(long totalDrivenTime) {
        this.totalDrivenTime = totalDrivenTime;
    }

    public List<DailyLog> getDailyLogs() {
        return dailyLogs;
    }

    public void setDailyLogs(List<DailyLog> dailyLogs) {
        this.dailyLogs = dailyLogs;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

}
