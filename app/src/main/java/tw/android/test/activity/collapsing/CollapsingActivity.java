package tw.android.test.activity.collapsing;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.hermes.test.R;

import butterknife.BindView;
import tw.android.test.BaseSimpleActivity;
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

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, CollapsingActivity.class);
        activity.startActivity(intent);
    }
}
