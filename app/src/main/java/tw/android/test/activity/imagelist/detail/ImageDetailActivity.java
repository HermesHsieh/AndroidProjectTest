package tw.android.test.activity.imagelist.detail;

import android.app.Activity;
import android.content.Intent;

import com.example.hermes.test.R;

import tw.android.test.base.BaseSimpleActivity;

/**
 * Created by hermes.hsieh on 2017/12/22.
 */

public class ImageDetailActivity extends BaseSimpleActivity {

    private final static String NAME = "name";
    private String mName;

    private ImageDetailFragment fragment;

    public static void launch(Activity activity, String name) {
        Intent intent = new Intent();
        intent.setClass(activity, ImageDetailActivity.class);
        intent.putExtra(NAME, name);
        activity.startActivity(intent);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_fragment);
    }

    @Override
    protected void initView() {
        mName = getIntent().getStringExtra(NAME);

        fragment = (ImageDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_view);

        if (fragment == null) {
            fragment = ImageDetailFragment.newInstance(mName);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_view, fragment)
                    .commit();
        }
    }

    @Override
    protected void initData() {

    }
}
