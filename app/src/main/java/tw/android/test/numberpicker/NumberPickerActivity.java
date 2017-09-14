package tw.android.test.numberpicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tw.android.test.ui.EditTextItem;

/**
 * Created by hermes.hsieh on 2017/9/14.
 */

public class NumberPickerActivity extends AppCompatActivity {

    @BindView(R.id.layout_content)
    LinearLayout layoutContent;

    @BindView(R.id.my_button)
    Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_picker);
        ButterKnife.bind(this);

        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.number_picker);
        numberPicker.setValue(4);
        numberPicker.setMaxValue(1000);
        numberPicker.setMinValue(1);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.d("NumberPickerActivity", "Value : " + newVal);
            }
        });

        for (int i = 0; i < 50; i++) {
            layoutContent.addView(new EditTextItem(this, "[ " + i + " ]", "number:" + i + 1).getView());
        }
    }

    @OnClick(R.id.my_button)
    public void onClickButtonEvent() {
        Log.d("tester", "Child Count : " + layoutContent.getChildCount());
    }
}
