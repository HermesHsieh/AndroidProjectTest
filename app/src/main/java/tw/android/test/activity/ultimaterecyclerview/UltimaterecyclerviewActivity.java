package tw.android.test.activity.ultimaterecyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.example.hermes.test.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import butterknife.BindView;
import tw.android.test.base.BaseSimpleActivity;

/**
 * Created by hermes.hsieh on 2017/9/17.
 */

public class UltimaterecyclerviewActivity extends BaseSimpleActivity {

    public static final int DELAY_MILLIS = 3000;

    @BindView(R.id.ultimate_recycler_view)
    UltimateRecyclerView mUltimateRecyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    UltimateListDataAdapter mAdapter;

    Handler mHandler;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_ultimaterecyclerview);
    }

    @Override
    protected void initView() {
        mHandler = new Handler();
        mAdapter = new UltimateListDataAdapter(this);

        enableLoadMore();
        hideLoading();

        mUltimateRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUltimateRecyclerView.setLoadMoreView(new ProgressBar(this));
        mUltimateRecyclerView.setEmptyView(R.layout.view_empty, UltimateRecyclerView.EMPTY_CLEAR_ALL);
//        mUltimateRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mUltimateRecyclerView.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, int maxLastVisiblePosition) {
                showLoading();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideLoading();
                        mAdapter.onAddData(20);
                        mAdapter.notifyDataSetChanged();
                        if (mAdapter.getAdapterItemCount() >= 100) {
                            disableLoadMore();
                        }
                    }
                }, DELAY_MILLIS);
            }
        });
        mUltimateRecyclerView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                showLoading();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        hideLoading();
                        initData();
                        if (mAdapter.getAdapterItemCount() < 100) {
                            enableLoadMore();
                        }
                    }
                }, DELAY_MILLIS);
            }
        });

        mUltimateRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mAdapter.onCreateData(20);
        mAdapter.notifyDataSetChanged();
    }

    public void enableLoadMore() {
        mUltimateRecyclerView.reenableLoadmore();
    }

    public void disableLoadMore() {
        mUltimateRecyclerView.disableLoadmore();
    }

    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, UltimaterecyclerviewActivity.class);
        activity.startActivity(intent);
    }
}
