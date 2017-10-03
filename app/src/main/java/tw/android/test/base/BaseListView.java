package tw.android.test.base;

import android.view.View;

/**
 * Created by hermes.hsieh on 2017/10/3.
 */

public interface BaseListView {
    void setListData();

    void addListData();

    void clearListData();

    void loadMoreComplete();

    void loadMoreEnd(boolean isEnd);

    void loadMoreFail(String msg);

    void setListLoadingView(View view);

    void setListEmptyView(View view);

    void setListEmptyViewText(String msg);
}
