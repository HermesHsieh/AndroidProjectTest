package tw.android.test.module;

import java.util.Calendar;

/**
 * Created by hermes.hsieh on 2017/11/15.
 */

public interface PickDateModel {
    Calendar getDefaultStartCalendar();

    Calendar getDefaultEndCalendar();

    Calendar getStartCalendar();

    Calendar getEndCalendar();

    void setStartCalendar(Calendar calendar);

    void setEndCalendar(Calendar calendar);

    void setStartDate(int year, int monthOfYear, int dayOfMonth);

    void setEndDate(int year, int monthOfYear, int dayOfMonth);

    long getPickMinDate();

    long getPickMaxDate();

    long getDefaultPickMaxDate();

    boolean isValidPickDate();

    boolean isSetStartDate();

    boolean isSetEndDate();

    String getStartDateFormat();

    String getEndDateFormat();
}
