package tw.android.test.activity.baserecyclerviewadapterhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.example.hermes.test.R;
import com.git4u.library_android.base.BaseRecyclerViewFragment;

public class SampleFragment extends BaseRecyclerViewFragment<SamplePresenter, String> implements SampleView {

    @Override
    protected BaseQuickAdapter<String, BaseViewHolder> initAdapter() {
        return new LoadMoreAdapter(50);
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.recycler_view;
    }

    @Override
    protected int getSwipeRefreshLayoutId() {
        return 0;
    }

    @Override
    protected LoadMoreView initLoadMoreView() {
        return new TestLoadMoreView();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_base_recycler_view_adapter_helper;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragment = (ViewGroup) view;
        mParent = (ViewGroup) mFragment.getParent();
    }

    protected ViewGroup mParent = null;
    protected ViewGroup mFragment = null;

    protected void showDataNotFoundView() {
        if (mFragment != null && mParent != null) {
            mFragment.setVisibility(View.INVISIBLE);
            View.inflate(getContext(), R.layout.view_empty, mParent);
        }
    }
}
