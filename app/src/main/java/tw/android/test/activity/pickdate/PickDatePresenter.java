package tw.android.test.activity.pickdate;

import java.util.Calendar;

import tw.android.test.module.PickDateModel;
import tw.android.test.module.PickDateModelImpl;

/**
 * Created by hermes.hsieh on 2017/11/17.
 */

public class PickDatePresenter implements PickDateContract.Presenter {

    PickDateContract.View mView;

    PickDateModel mModel;

    public PickDatePresenter(PickDateContract.View view) {
        mView = view;
        mModel = new PickDateModelImpl();
    }

    @Override
    public void initDateText() {
        mView.setStartDateText(mModel.getStartDateFormat());
        mView.setEndDateText(mModel.getEndDateFormat());
    }

    @Override
    public void onClickPickStartDate() {
        Calendar calendar = mModel.getStartCalendar();
        mView.showStartDatePickerDialog(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                mModel.getEndCalendar().getTimeInMillis()
        );
    }

    @Override
    public void onClickPickEndDate() {
        Calendar calendar = mModel.getEndCalendar();
        mView.showEndDatePickerDialog(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                mModel.getPickMinDate(),
                mModel.getDefaultPickMaxDate()
        );
    }

    @Override
    public void onClickPickDate() {
        if (mModel.isValidPickDate()) {
            mView.setStartDateText("PickStart");
            mView.setEndDateText("PickEnd");
            mView.setStartDateError(null);
            mView.setEndDateError(null);
        } else {
            mView.setStartDateError("error");
            mView.setEndDateError("error");
        }
    }

    @Override
    public void onSetStartDate(int year, int mouth, int day) {
        mModel.setStartDate(year, mouth, day);
        mView.setStartDateText(mModel.getStartDateFormat());
    }

    @Override
    public void onSetEndDate(int year, int mouth, int day) {
        mModel.setEndDate(year, mouth, day);
        mView.setEndDateText(mModel.getEndDateFormat());
    }
}
