package tw.android.test.activity.pickdate;

/**
 * Created by hermes.hsieh on 2017/11/17.
 */

public interface PickDateContract {

    interface View {
        void setStartDateText(String startDateText);

        void setEndDateText(String endDateText);

        void setStartDateError(String msg);

        void setEndDateError(String msg);

        void showStartDatePickerDialog(int year, int mouth, int day, long maxDate);

        void showEndDatePickerDialog(int year, int mouth, int day, long minDate, long maxDate);
    }

    interface Presenter {
        void onClickPickStartDate();

        void onClickPickEndDate();

        void onClickPickDate();

        void onSetStartDate(int year, int mouth, int day);

        void onSetEndDate(int year, int mouth, int day);
    }
}
