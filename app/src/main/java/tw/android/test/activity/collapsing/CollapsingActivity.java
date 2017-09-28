package tw.android.test.activity.collapsing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.OnClick;
import tw.android.test.BaseSimpleActivity;
import tw.android.test.activity.home.MainActivity;
import tw.android.test.activity.numberpicker.NumberPickerActivity;
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

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int initContentView() {
        return R.layout.activity_collapsing;
    }

    @Override
    protected void initView() {
        setTitle(this.getClass().getSimpleName());
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }

        mAdapter = new ListDataAdapter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
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
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, CollapsingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    @OnClick(R.id.bottom_button_left)
    public void onClickBottomButtonLeft() {
        MainActivity.launch(this);
    }

    @OnClick(R.id.bottom_button_right)
    public void onClickBottomButtonRight() {
        NumberPickerActivity.launch(this);
    }
}
