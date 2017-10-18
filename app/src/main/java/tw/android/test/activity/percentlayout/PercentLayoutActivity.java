package tw.android.test.activity.percentlayout;

import android.app.Activity;
import android.content.Intent;

import com.example.hermes.test.R;

import tw.android.test.BaseSimpleActivity;


/**
 * Created by hermes on 2017/10/18.
 */

public class PercentLayoutActivity extends BaseSimpleActivity {

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, PercentLayoutActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initContentView() {
        return R.layout.activity_percentlayout;
    }
}
