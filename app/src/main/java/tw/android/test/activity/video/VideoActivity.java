package tw.android.test.activity.video;

import android.app.Activity;
import android.content.Intent;

import tw.android.test.base.BaseSimpleActivity;

public class VideoActivity extends BaseSimpleActivity {

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, VideoActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void setContentView() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
