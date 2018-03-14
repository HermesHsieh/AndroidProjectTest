package tw.android.test.activity.theme;

import android.app.Activity;
import android.content.Intent;

import com.example.hermes.test.R;

import tw.android.test.base.BaseSimpleActivity;


public class ThemeActivity extends BaseSimpleActivity {

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ThemeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_theme);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
