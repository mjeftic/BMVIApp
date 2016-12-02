package unima.bmvidatarun.truckoo.model;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by Mukizen on 02.12.2016.
 */

public class DailyLog  {

    private Date day;
    private long drivenSinceLastPause;
    private long drivenToday;

    public DailyLog(Date day) {
        this.day = day;
    }

    public long getDrivenToday() {
        return drivenToday;
    }

    public void setDrivenToday(long drivenToday) {
        this.drivenToday = drivenToday;
    }

    public long getDrivenSinceLastPause() {
        return drivenSinceLastPause;
    }

    public void setDrivenSinceLastPause(long drivenSinceLastPause) {
        this.drivenSinceLastPause = drivenSinceLastPause;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
}
