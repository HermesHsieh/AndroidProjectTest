package tw.android.test.activity.inputtextlayout;

import android.app.Activity;
import android.content.Intent;

import com.example.hermes.test.R;

import tw.android.test.base.BaseSimpleActivity;

/**
 * Created by hermes.hsieh on 2018/1/20.
 */

public class InputTextLayoutActivity extends BaseSimpleActivity {

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, InputTextLayoutActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_input_text_layout);
    }

    @Override
    protected void initView() {
        setTitle(this.getClass().getSimpleName());
    }

    @Override
    protected void initData() {

    }
}
