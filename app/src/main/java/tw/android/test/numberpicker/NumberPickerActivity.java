package tw.android.test.numberpicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.NumberPicker;

import com.example.hermes.test.R;

/**
 * Created by hermes.hsieh on 2017/9/14.
 */

public class NumberPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_picker);

        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.number_picker);
        numberPicker.setValue(4);
        numberPicker.setMaxValue(1000);
        numberPicker.setMinValue(1);
//        numberPicker.setDisplayedValues(new String[]{"1", "2", "3", "4", "5"});
//        numberPicker.setFormatter(new NumberPicker.Formatter() {
//            @Override
//            public String format(int value) {
//                return String.valueOf(value);
//            }
//        });
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.d("NumberPickerActivity", "Value : " + newVal);
            }
        });
    }
}
