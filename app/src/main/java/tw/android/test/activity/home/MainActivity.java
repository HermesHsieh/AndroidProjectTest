package tw.android.test.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.OnClick;
import tw.android.test.BaseSimpleActivity;
import tw.android.test.GlobalConstant;
import tw.android.test.activity.percentlayout.PercentLayoutActivity;
import tw.android.test.LocaleChangedReceiver;
import tw.android.test.activity.baserecyclerviewadapterhelper.BaseRecyclerViewAdapterHelperActivity;
import tw.android.test.activity.collapsing.CollapsingActivity;
import tw.android.test.activity.numberpicker.NumberPickerActivity;
import tw.android.test.activity.search.SearchActivity;
import tw.android.test.activity.ultimaterecyclerview.UltimaterecyclerviewActivity;

public class MainActivity extends BaseSimpleActivity {

    @BindView(R.id.collapsing)
    Button collapsing;

    @BindView(R.id.number_picker)
    Button numberPicker;

    @BindView(R.id.constrain_layout)
    ConstraintLayout mConstraintLayout;

    @BindView(R.id.icon)
    ImageView icon;

    @BindView(R.id.amount)
    TextView amount;
    private LocaleChangedReceiver mReceiver;

    @GlobalConstant.WeekDays
    int currentDay;

    public void setCurrentDay(@GlobalConstant.WeekDays int currentDay) {
        this.currentDay = currentDay;
    }

    @GlobalConstant.WeekDays
    public int getCurrentDay() {
        return currentDay;
    }

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

        mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click layout", Toast.LENGTH_SHORT).show();
            }
        });

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click icon", Toast.LENGTH_SHORT).show();
            }
        });

//        amount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Click amount", Toast.LENGTH_SHORT).show();
//            }
//        });

        IntentFilter filter = new IntentFilter(Intent.ACTION_LOCALE_CHANGED);
        mReceiver = new LocaleChangedReceiver();
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
            mReceiver = null;
        }
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

    @OnClick(R.id.menu_action_search)
    public void onClickSearchButton() {
        SearchActivity.launch(this);
    }

    @OnClick(R.id.ultimate_recycler_view)
    public void onClickUltimateRecyclerViewButton() {
        UltimaterecyclerviewActivity.launch(this);
    }

    @OnClick(R.id.base_recycler_view_adapter_helper)
    public void onClickBaseRecyclerViewAdapterHelperButton() {
        BaseRecyclerViewAdapterHelperActivity.launch(this);
    }

    @OnClick(R.id.percent_layout)
    public void onClickPercentLayoutButton() {
        PercentLayoutActivity.launch(this);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
}
