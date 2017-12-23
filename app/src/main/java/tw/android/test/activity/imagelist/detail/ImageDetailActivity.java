package tw.android.test.activity.imagelist.detail;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

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
        Intent intent = new Intent(activity, ImageDetailActivity.class);
        intent.putExtra(NAME, name);
        activity.startActivity(intent);
    }

    public static void launch(Activity activity, String name, View view) {
        Intent intent = new Intent(activity, ImageDetailActivity.class);
        intent.putExtra(NAME, name);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity, view, "image_trans");
        activity.startActivity(intent, options.toBundle());
    }

    public static void launch(Activity activity, String name, View image, View nameView) {
        Intent intent = new Intent(activity, ImageDetailActivity.class);
        intent.putExtra(NAME, name);

        Pair<View, String> p1 = Pair.create(image, "image_trans");
        Pair<View, String> p2 = Pair.create(nameView, "name_trans");
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity, p1, p2);
        activity.startActivity(intent, options.toBundle());
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
