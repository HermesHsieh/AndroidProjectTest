package tw.android.test.activity.collapsing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.OnClick;
import tw.android.test.base.BaseSimpleActivity;
import tw.android.test.ui.form.FormView;


/**
 * Created by hermes on 2017/9/12.
 */

public class CollapsingActivity extends BaseSimpleActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    ListDataAdapter mAdapter;

    private FormView.Adapter mFormAdapter;

    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;

//    @BindView(R.id.toolbar)
//    Toolbar mToolbar;

    @BindView(R.id.fab_scroll)
    FloatingActionButton fabScroll;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_collapsing);
    }

    @Override
    protected void initView() {
        setTitle(this.getClass().getSimpleName());
//        if (mToolbar != null) {
//            setSupportActionBar(mToolbar);
//        }

        mAdapter = new ListDataAdapter(this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("ttt", "First visible item position : " + mLayoutManager.findFirstVisibleItemPosition());

                if (mLayoutManager.findFirstVisibleItemPosition() < 15) {
                    if (fabScroll.isShown()) {
                        fabScroll.hide();
                    }
                } else {
                    if (!fabScroll.isShown()) {
                        fabScroll.show();
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {
        mAdapter.onCreateData(50);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Collapsing", "onCreate--->");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Collapsing", "onStart--->");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Collapsing", "onResume--->");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Collapsing", "onPause--->");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Collapsing", "onStop--->");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Collapsing", "onDestroy--->");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("Collapsing", "onNewIntent--->");
        mRecyclerView.scrollToPosition(0);
        mAdapter.onCreateData(30);
        mAdapter.notifyDataSetChanged();

        mBuilder = new Builder().setTitle("title").build();
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, CollapsingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    private Builder mBuilder;

    public static class Builder {

        String title;

        Builder() {

        }

        public Builder build() {
            return new Builder();
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
    }

    public static void launch(Activity activity, Builder builder) {
        Intent intent = new Intent(activity, CollapsingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("BUILDER", builder.toString());
        activity.startActivity(intent);
    }

    public static void launch(Activity activity, View view) {
        Intent intent = new Intent(activity, CollapsingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity, view, "android_image");
        activity.startActivity(intent, options.toBundle());
    }

    @OnClick(R.id.bottom_button_left)
    public void onClickBottomButtonLeft() {
//        MainActivity.launch(this);
        mRecyclerView.smoothScrollToPosition(0);
    }

    @OnClick(R.id.bottom_button_right)
    public void onClickBottomButtonRight() {
//        NumberPickerActivity.launch(this);
        ((LinearLayoutManager) mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(0, 0);
    }

    @OnClick(R.id.fab_scroll)
    public void onClickFloatActionButton() {
        mRecyclerView.smoothScrollToPosition(0);
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.tw/")));
    }
}
