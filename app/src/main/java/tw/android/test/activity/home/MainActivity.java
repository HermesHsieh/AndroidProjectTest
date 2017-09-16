package tw.android.test.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;

import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.OnClick;
import tw.android.test.BaseSimpleActivity;
import tw.android.test.activity.collapsing.CollapsingActivity;
import tw.android.test.activity.numberpicker.NumberPickerActivity;

public class MainActivity extends BaseSimpleActivity {

    @BindView(R.id.collapsing)
    Button collapsing;

    @BindView(R.id.number_picker)
    Button numberPicker;

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        displayHomeButton(false);
//        Book book = new Book();
//        Book book2 = Book.of(2, 4, "Hello");
//        Book book3 = Book.of(4, 4, "");
//        new Book.NoArgsExample();
//        book.
//        Log.d(BOOK, "Book Id : " + book.getId());
//        Log.d(BOOK, "Book Name : " + book.getName());
//        Log.d(BOOK, "Book Date : " + book.getDate());
//        Log.d(BOOK, "Book Status : " + book.isStatus());
//        Log.d(BOOK, "Book toString : " + book.toString());

//        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, AlarmReceiver.class);
//        intent.setAction(ACTION_REPEAT_ALARM);
//        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
//
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
//                90 * 1000, alarmIntent);
    }

    @Override
    protected void initData() {
    }

    @OnClick(R.id.collapsing)
    public void onClickCollapsingButton() {
        CollapsingActivity.launch(this);
    }

    @OnClick(R.id.number_picker)
    public void onClickNumberPickerButton() {
        NumberPickerActivity.launch(this);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
}
