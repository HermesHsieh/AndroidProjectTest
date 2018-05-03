package tw.android.test.activity.baserecyclerviewadapterhelper;

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
}
