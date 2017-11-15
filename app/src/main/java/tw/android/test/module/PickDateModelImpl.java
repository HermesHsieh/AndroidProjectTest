package tw.android.test.module;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by hermes.hsieh on 2017/11/15.
 */

public class PickDateModelImpl implements PickDateModel {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_TIME_MIN_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat DATE_TIME_SEC_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final int PICK_MAX_DATE_OF_MONTH_FROM_START_DATE = 1;

    private Calendar startCalendar;
    private Calendar endCalendar;

    public Calendar getDefaultStartCalendar() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    public Calendar getDefaultEndCalendar() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar;
    }

    public Calendar getStartCalendar() {
        return startCalendar != null ? startCalendar : getDefaultStartCalendar();
    }

    public Calendar getEndCalendar() {
        return endCalendar != null ? endCalendar : getDefaultEndCalendar();
    }

    public void setStartCalendar(Calendar calendar) {
        this.startCalendar = calendar;
    }

    public void setEndCalendar(Calendar calendar) {
        this.endCalendar = calendar;
    }

    public void setStartDate(int year, int monthOfYear, int dayOfMonth) {
        startCalendar = new GregorianCalendar();
        startCalendar.set(Calendar.YEAR, year);
        startCalendar.set(Calendar.MONTH, monthOfYear);
        startCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);
    }

    public void setEndDate(int year, int monthOfYear, int dayOfMonth) {
        endCalendar = new GregorianCalendar();
        endCalendar.set(Calendar.YEAR, year);
        endCalendar.set(Calendar.MONTH, monthOfYear);
        endCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        endCalendar.set(Calendar.HOUR_OF_DAY, 23);
        endCalendar.set(Calendar.MINUTE, 59);
        endCalendar.set(Calendar.SECOND, 59);
    }

    public long getPickMinDate() {
        if (startCalendar != null) {
            return startCalendar.getTimeInMillis();
        } else {
            return -1;
        }
    }

    public long getPickMaxDate() {
        if (startCalendar != null) {
            Calendar max = new GregorianCalendar();
            max.setTime(startCalendar.getTime());
            max.add(Calendar.MONTH, PICK_MAX_DATE_OF_MONTH_FROM_START_DATE);
            max.set(Calendar.HOUR_OF_DAY, 23);
            max.set(Calendar.MINUTE, 59);
            max.set(Calendar.SECOND, 59);
            return max.getTimeInMillis();
        } else {
            return -1;
        }
    }

    public boolean isValidPickDate() {
        return getEndCalendar().after(getStartCalendar());
    }

    public String getStartDateFormat() {
        if (startCalendar != null) {
            return DATE_FORMAT.format(startCalendar.getTime());
        } else {
            return "";
        }
    }

    public String getEndDateFormat() {
        if (endCalendar != null) {
            return DATE_FORMAT.format(endCalendar.getTime());
        } else {
            return "";
        }
    }
}
