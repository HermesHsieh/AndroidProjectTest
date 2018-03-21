package tw.android.test.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.OnClick;
import tw.android.test.LocaleChangedReceiver;
import tw.android.test.activity.anko.AnkoActivity;
import tw.android.test.activity.baserecyclerviewadapterhelper.BaseRecyclerViewAdapterHelperActivity;
import tw.android.test.activity.collapsing.CollapsingActivity;
import tw.android.test.activity.databinding.DataBindingActivity;
import tw.android.test.activity.dialog.DialogActivity;
import tw.android.test.activity.image.ImageActivity;
import tw.android.test.activity.inputtextlayout.InputTextLayoutActivity;
import tw.android.test.activity.login.LoginActivity;
import tw.android.test.activity.numberpicker.NumberPickerActivity;
import tw.android.test.activity.percentlayout.PercentLayoutActivity;
import tw.android.test.activity.pickdate.PickDateActivity;
import tw.android.test.activity.rxjava.RxJavaActivity;
import tw.android.test.activity.search.SearchActivity;
import tw.android.test.activity.theme.ThemeActivity;
import tw.android.test.activity.ultimaterecyclerview.UltimaterecyclerviewActivity;
import tw.android.test.activity.video.VideoActivity;
import tw.android.test.base.BaseSimpleActivity;
import tw.android.test.cache.GlobalConstant;
import tw.android.test.ui.CurrencyInputEditText;
import tw.android.test.ui.view.ImageViewColored;

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

    @BindView(R.id.currency_input)
    CurrencyInputEditText currencyInputEditText;

    @BindView(R.id.currency_input_layout)
    TextInputLayout currency_input_layout;

    @BindView(R.id.image_view)
    ImageView imageView;

    @BindView(R.id.image_view3)
    ImageView imageView3;

    @BindView(R.id.image_view4)
    ImageViewColored imageView4;

    @BindView(R.id.textViewDrawable)
    TextView textViewDrawable;

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
    protected void setContentView() {
        setContentView(R.layout.activity_main);
//        return R.layout.activity_main;
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

        currencyInputEditText.setOnAfterTextChangedListener(new CurrencyInputEditText.OnAfterTextChangedListener() {
            @Override
            public void onAfterTextChanged(String text) {
                Log.d("MainActivity", "text : " + text);
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

//        ThemeActivity.launch(this);

//        Drawable mDrawable = getResources().getDrawable(R.mipmap.sort_like);
//        mDrawable.setColorFilter(new PorterDuffColorFilter(0xffff00, PorterDuff.Mode.MULTIPLY));
//        imageView3.setImageDrawable(mDrawable);

        imageView3.setColorFilter(ContextCompat.getColor(this, R.color.pf_mall_button_primary));

//        imageView4.setColorFilter(ContextCompat.getColor(this, R.color.deep_blue_700));
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
//        currencyInputEditText.setText("1245.623");
    }

    @OnClick(R.id.collapsing)
    public void onClickCollapsingButton() {
        CollapsingActivity.launch(this, imageView);
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

    @OnClick(R.id.percent_layout)
    public void onClickPercentLayoutButton() {
        PercentLayoutActivity.launch(this);
    }

    @OnClick(R.id.base_recycler_view_adapter_helper)
    public void onClickBaseRecyclerViewAdapterHelperButton() {
        BaseRecyclerViewAdapterHelperActivity.launch(this);
    }

    @OnClick(R.id.data_binding)
    public void onClickDataBindingButton() {
        DataBindingActivity.launch(this);
    }

    @OnClick(R.id.login)
    public void onClickLoginButton() {
        LoginActivity.launch(this);
    }

    @OnClick(R.id.pick_date)
    public void onClickPickDateButton() {
        PickDateActivity.launch(this);
    }

    @OnClick(R.id.dialog)
    public void onClickDialogButton() {
        DialogActivity.launch(this);
    }

    @OnClick(R.id.rxjava)
    public void onClickRxJavaButton() {
        RxJavaActivity.launch(this);
    }

    @OnClick(R.id.text_input_layout)
    public void onClickTextInputLayout() {
        InputTextLayoutActivity.launch(this);
    }

    @OnClick(R.id.image_button)
    public void onClickImageButton() {
        ImageActivity.launch(this);
    }

    @OnClick(R.id.theme)
    public void onClickThemeButton() {
        ThemeActivity.launch(this);
    }

    @OnClick(R.id.video)
    public void onClickVideoButton() {
        VideoActivity.launch(this);
    }

    @OnClick(R.id.anko)
    public void onClickAnkoButton() {
        Intent intent = new Intent(this, AnkoActivity.class);
        startActivity(intent);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    private boolean isColored = false;

    @OnClick(R.id.image_view4)
    public void onChangeColored() {
        if (isColored) {
            imageView4.setColored(R.color.indigo_800);
        } else {
            imageView4.setColored(R.color.yellow_900);
        }
        isColored = !isColored;
    }

}
