package tw.android.test.activity.pickdate;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.constraint.Guideline;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.OnClick;
import tw.android.test.base.BaseSimpleActivity;

/**
 * Created by hermes.hsieh on 2017/11/17.
 */

public class PickDateActivity extends BaseSimpleActivity implements PickDateContract.View {

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
