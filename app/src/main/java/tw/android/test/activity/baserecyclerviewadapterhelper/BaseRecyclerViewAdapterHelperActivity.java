package tw.android.test.activity.baserecyclerviewadapterhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.hermes.test.R;

import butterknife.BindView;
import tw.android.test.BaseSimpleActivity;
import tw.android.test.data.DataServer;

/**
 * Created by hermes.hsieh on 2017/9/17.
 */

public class BaseRecyclerViewAdapterHelperActivity extends BaseSimpleActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    LoadMoreAdapter mLoadMoreAdapter;
    private boolean isError;

    private static final int TOTAL_COUNTER = 100;
    private static final int PAGE_SIZE = 20;

    private int mCurrentCounter = 0;
    private View notDataView;

    @Override
    protected int initContentView() {
        return R.layout.activity_base_recycler_view_adapter_helper;
    }

    @Override
    protected void initView() {
        mLoadMoreAdapter = new LoadMoreAdapter(0);
        mLoadMoreAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mLoadMoreAdapter.setLoadMoreView(new TestLoadMoreView());
        mLoadMoreAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);

        notDataView = getLayoutInflater().inflate(R.layout.view_empty, (ViewGroup) mRecyclerView.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });

        mLoadMoreAdapter.setEmptyView(notDataView);

//        mLoadMoreAdapter.setEmptyView(R.layout.view_empty);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.setAdapter(mLoadMoreAdapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onLoadMoreRequested() {
        Log.d("BaseRecyclerView", "onLoadMoreRequested");
        Log.d("BaseRecyclerView", "data size : " + mLoadMoreAdapter.getData().size());
        int size = mLoadMoreAdapter.getData().size();
        if (size >= TOTAL_COUNTER) {
            mLoadMoreAdapter.loadMoreEnd(true);
            return;
        }

        mLoadMoreAdapter.addData(DataServer.getStringDataList(size, PAGE_SIZE));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadMoreAdapter.loadMoreComplete();
            }
        }, 3000);
    }

    @Override
    public void onRefresh() {
        Log.d("BaseRecyclerView", "onRefresh");
        mLoadMoreAdapter.setEmptyView(R.layout.view_item_loading, (ViewGroup) mRecyclerView.getParent());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadMoreAdapter.setNewData(DataServer.getStringDataList(PAGE_SIZE));
            }
        }, 3000);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, BaseRecyclerViewAdapterHelperActivity.class);
        activity.startActivity(intent);
    }
}
