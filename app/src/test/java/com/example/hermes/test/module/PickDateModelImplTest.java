package com.example.hermes.test.module;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import tw.android.test.module.PickDateModel;
import tw.android.test.module.PickDateModelImpl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;


/**
 * Created by hermes.hsieh on 2017/11/15.
 */

public class PickDateModelImplTest {

    private String lastDayOfBeforeMonthFormat;
    private String dayOfMonthFormat;

    @Before
    public void setLastDayOfBeforeMonthFormat() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        lastDayOfBeforeMonthFormat = PickDateModelImpl.DATE_FORMAT.format(calendar.getTime());
    }

    @Before
    public void setDayOfMonthFormat() {
        dayOfMonthFormat = PickDateModelImpl.DATE_FORMAT.format(new Date());
    }

    @Test
    public void get_default_start_date_format() {
        PickDateModel model = new PickDateModelImpl();
        assertEquals(lastDayOfBeforeMonthFormat, model.getStartDateFormat());
        assertNotSame(dayOfMonthFormat, model.getStartDateFormat());
    }

    @Test
    public void get_default_end_date_format() {
        PickDateModel model = new PickDateModelImpl();
        assertEquals(dayOfMonthFormat, model.getEndDateFormat());
        assertNotSame(lastDayOfBeforeMonthFormat, model.getEndDateFormat());
    }

    @Test
    public void get_set_start_date_calendar() {
        PickDateModel model = new PickDateModelImpl();
        Calendar calendar = new GregorianCalendar();
        model.setStartCalendar(calendar);
        assertEquals(calendar, model.getStartCalendar());
    }

    @Test
    public void get_set_end_date_calendar() {
        PickDateModel model = new PickDateModelImpl();
        Calendar calendar = new GregorianCalendar();
        model.setEndCalendar(calendar);
        assertEquals(calendar, model.getEndCalendar());
    }

    @Test
    public void set_start_date() {
        PickDateModel model = new PickDateModelImpl();
        model.setStartDate(2017, 8, 14);
        Calendar calendar = new GregorianCalendar();
        calendar.set(2017, 8, 14, 0, 0, 0);
        assertEquals("The pick date model output should equal to calendar instance",
                calendar, model.getStartCalendar());
    }

    @Test
    public void set_end_date() {
        PickDateModel model = new PickDateModelImpl();
        model.setEndDate(2017, 8, 14);
        Calendar calendar = new GregorianCalendar();
        calendar.set(2017, 8, 14, 23, 59, 59);
        assertEquals(calendar, model.getEndCalendar());
    }

    @Test
    public void get_pick_min_date_have_start_date() {
        PickDateModel model = new PickDateModelImpl();
        model.setStartDate(2017, 8, 14);
        Calendar calendar = new GregorianCalendar();
        calendar.set(2017, 8, 14, 0, 0, 0);
        assertEquals(calendar.getTimeInMillis(), model.getPickMinDate());
    }

    @Test
    public void get_pick_min_date_not_start_date() {
        PickDateModel model = new PickDateModelImpl();
        assertEquals(-1, model.getPickMinDate());
    }

    @Test
    public void get_pic_max_date() {
        PickDateModel model = new PickDateModelImpl();
        Calendar calendar = new GregorianCalendar();
//        calendar.set(Calendar.HOUR_OF_DAY, 23);
//        calendar.set(Calendar.MINUTE, 59);
//        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        assertEquals(calendar.getTimeInMillis(), model.getPickMaxDate());
    }

    @Test
    public void is_valid_pick_date_true() {
        PickDateModel model = new PickDateModelImpl();
        model.setStartDate(2017, 8, 31);
        model.setEndDate(2017, 10, 21);
        assertEquals(true, model.isValidPickDate());
    }

    @Test
    public void is_valid_pick_date_false() {
        PickDateModel model = new PickDateModelImpl();
        model.setEndDate(2017, 8, 31);
        model.setStartDate(2017, 10, 21);
        assertEquals(false, model.isValidPickDate());
    }

    @Test
    public void user_set_start_date() {
        PickDateModel model = new PickDateModelImpl();
        model.setStartCalendar(new GregorianCalendar());
        assertEquals(true, model.isSetStartDate());
    }

    @Test
    public void user_not_set_start_date() {
        PickDateModel model = new PickDateModelImpl();
        assertEquals(false, model.isSetStartDate());
    }

    @Test
    public void user_set_end_date() {
        PickDateModel model = new PickDateModelImpl();
        model.setStartCalendar(new GregorianCalendar());
        assertEquals(true, model.isSetEndDate());
    }

    @Test
    public void user_not_set_end_date() {
        PickDateModel model = new PickDateModelImpl();
        assertEquals(false, model.isSetEndDate());
    }
}
