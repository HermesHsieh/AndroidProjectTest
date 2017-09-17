package tw.android.test.activity.baserecyclerviewadapterhelper;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.example.hermes.test.R;

/**
 * Created by hermes.hsieh on 2017/9/17.
 */

public class TestLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.view_item_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
