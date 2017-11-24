package tw.android.test.activity.pickdate;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.constraint.Guideline;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.hermes.test.R;

import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import tw.android.test.base.BaseSimpleActivity;

/**
 * Created by hermes.hsieh on 2017/11/17.
 */

public class PickDateActivity extends BaseSimpleActivity implements PickDateContract.View {

    private final static String TAG = PickDateActivity.class.getSimpleName();

    @BindView(R.id.guide_line)
    Guideline guideLine;
    @BindView(R.id.start_date)
    TextView startDate;
    @BindView(R.id.start_date_layout)
    TextInputLayout startDateLayout;
    @BindView(R.id.end_date)
    TextView endDate;
    @BindView(R.id.end_date_layout)
    TextInputLayout endDateLayout;
    @BindView(R.id.pick)
    Button pick;

    PickDateContract.Presenter mPresenter;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, PickDateActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_pick_date);
    }

    @Override
    protected void initView() {
        mPresenter = new PickDatePresenter(this);
    }

    @Override
    protected void initData() {
        startDate.setText("請選擇開始日期");
        endDate.setText("請選擇結束日期");
        testOffSetDateTime();
    }

    private void testOffSetDateTime() {
        // 2017-11-16T12:28:48.827Z
        OffsetDateTime offsetDateTime1 = OffsetDateTime.of(2017, 11, 16, 12, 28, 48, 0, ZoneOffset.ofHours(0));
        Log.d(TAG, "OffsetDateTime1 : " + offsetDateTime1);

        OffsetDateTime offsetDateTime2 = OffsetDateTime.of(2017, 11, 16, 12, 28, 48, 0, ZoneOffset.ofHours(8));
        Log.d(TAG, "OffsetDateTime2 : " + offsetDateTime2);

        OffsetDateTime offsetDateTime3 = OffsetDateTime.of(2017, 11, 16, 12, 28, 48, 0, ZoneOffset.of("+08:00"));
        Log.d(TAG, "OffsetDateTime3 : " + offsetDateTime3);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.TAIWAN);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.JAPAN);
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a", Locale.US);

        Log.d(TAG, "Time1 Formatter1 : " + getAppDateTimeNormal(offsetDateTime1));

//        Log.d(TAG, "Time1 Formatter2 : " + offsetDateTime1.format(formatter2));
//        Log.d(TAG, "Time1 Formatter3 : " + offsetDateTime1.format(formatter3));
//        Log.d(TAG, "Time3 Formatter1 : " + offsetDateTime3.format(formatter));
//        Log.d(TAG, "Time3 Formatter2 : " + offsetDateTime3.format(formatter2));
//        Log.d(TAG, "Time3 Formatter3 : " + offsetDateTime3.format(formatter3));

        startDate.setText(offsetDateTime3.format(formatter3));
    }

    private static DateTimeFormatter DATE_TIME_LAST_SECS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.TAIWAN);
    private static DateTimeFormatter DATE_TIME_LAST_NANO_SECS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS", Locale.TAIWAN);
    private static DateTimeFormatter DATE_TIME_LAST_MINS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.TAIWAN);
    private static DateTimeFormatter DATE_TIME_LAST_DAYS = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.TAIWAN);
    private static DateTimeFormatter DATE_TIME_LAST_SECS_WITH_AM_PM = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a", Locale.US);

    public static String getAppDateTimeNormal(OffsetDateTime offsetDateTime) {
        return getDateTimeFormat(DATE_TIME_LAST_SECS, offsetDateTime);
    }

    private static String getDateTimeFormat(DateTimeFormatter dateTimeFormatter, OffsetDateTime offsetDateTime) {
        return offsetDateTime.format(dateTimeFormatter);
    }

    @OnClick({R.id.start_date, R.id.end_date, R.id.pick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start_date:
                mPresenter.onClickPickStartDate();
                break;
            case R.id.end_date:
                mPresenter.onClickPickEndDate();
                break;
            case R.id.pick:
                mPresenter.onClickPickDate();
                break;
        }
    }

    @Override
    public void setStartDateText(String startDateText) {
        this.startDate.setText(startDateText);
    }

    @Override
    public void setEndDateText(String endDateText) {
        this.endDate.setText(endDateText);
    }

    @Override
    public void setStartDateError(String msg) {
        startDateLayout.setError(msg);
    }

    @Override
    public void setEndDateError(String msg) {
        endDateLayout.setError(msg);
    }

    @Override
    public void showStartDatePickerDialog(int year, int mouth, int day, long maxDate) {
        DatePickerDialog dialog = new DatePickerDialog(this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mPresenter.onSetStartDate(year, month, dayOfMonth);
            }
        }, year, mouth, day);

        dialog.getDatePicker().setMaxDate(maxDate);
        dialog.show();
    }

    @Override
    public void showEndDatePickerDialog(int year, int mouth, int day, long minDate, long maxDate) {
        DatePickerDialog dialog = new DatePickerDialog(this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mPresenter.onSetEndDate(year, month, dayOfMonth);
            }
        }, year, mouth, day);
        dialog.getDatePicker().setMinDate(minDate);
        dialog.getDatePicker().setMaxDate(maxDate);
        dialog.show();
    }
}
