package tw.android.test.module;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by hermes.hsieh on 2017/11/15.
 */
public class PickDateModelImpl implements PickDateModel {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_TIME_MIN_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat DATE_TIME_SEC_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final int PICK_MAX_DATE_OF_MONTH_FROM_START_DATE = 1;

    private Calendar startCalendar;
    private Calendar endCalendar;

    @Override
    public Calendar getDefaultStartCalendar() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    @Override
    public Calendar getDefaultEndCalendar() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar;
    }

    @Override
    public Calendar getStartCalendar() {
        return startCalendar != null ? startCalendar : getDefaultStartCalendar();
    }

    @Override
    public Calendar getEndCalendar() {
        return endCalendar != null ? endCalendar : getDefaultEndCalendar();
    }

    @Override
    public void setStartCalendar(Calendar calendar) {
        this.startCalendar = calendar;
    }

    @Override
    public void setEndCalendar(Calendar calendar) {
        this.endCalendar = calendar;
    }

    @Override
    public void setStartDate(int year, int monthOfYear, int dayOfMonth) {
        startCalendar = new GregorianCalendar();
        startCalendar.set(Calendar.YEAR, year);
        startCalendar.set(Calendar.MONTH, monthOfYear);
        startCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);
    }

    @Override
    public void setEndDate(int year, int monthOfYear, int dayOfMonth) {
        endCalendar = new GregorianCalendar();
        endCalendar.set(Calendar.YEAR, year);
        endCalendar.set(Calendar.MONTH, monthOfYear);
        endCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        endCalendar.set(Calendar.HOUR_OF_DAY, 23);
        endCalendar.set(Calendar.MINUTE, 59);
        endCalendar.set(Calendar.SECOND, 59);
    }

    @Override
    public long getPickMinDate() {
        if (startCalendar != null) {
            return startCalendar.getTimeInMillis();
        } else {
            return -1;
        }
    }

    @Override
    public long getPickMaxDate() {
        Calendar max = getEndCalendar();
        return max.getTimeInMillis();
    }

    @Override
    public long getDefaultPickMaxDate() {
        Calendar max = getDefaultEndCalendar();
        return max.getTimeInMillis();
    }

    @Override
    public boolean isValidPickDate() {
        return getEndCalendar().after(getStartCalendar());
    }

    @Override
    public String getStartDateFormat() {
        return DATE_FORMAT.format(getStartCalendar().getTime());
    }

    @Override
    public String getEndDateFormat() {
        return DATE_FORMAT.format(getEndCalendar().getTime());
    }
}
