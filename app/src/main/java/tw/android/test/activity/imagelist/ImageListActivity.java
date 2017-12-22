package tw.android.test.activity.imagelist;

import android.app.Activity;
import android.content.Intent;

import com.example.hermes.test.R;

import tw.android.test.base.BaseSimpleActivity;

/**
 * Created by hermes.hsieh on 2017/12/22.
 */

public class ImageListActivity extends BaseSimpleActivity {

    private ImageListFragment fragment;

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, ImageListActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_image_list);
    }

    @Override
    protected void initView() {
        fragment = (ImageListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_view);

        if (fragment == null) {
            fragment = ImageListFragment.newInstance();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_view, fragment)
                    .commit();
        }
    }

    @Override
    protected void initData() {

    }
}
