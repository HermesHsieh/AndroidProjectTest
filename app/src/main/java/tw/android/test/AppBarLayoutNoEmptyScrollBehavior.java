package tw.android.test;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by hermes.hsieh on 2017/9/15.
 */

class AppBarLayoutNoEmptyScrollBehavior extends AppBarLayout.Behavior {

    AppBarLayout mAppBarLayout;
    RecyclerView mRecyclerView;

    public AppBarLayoutNoEmptyScrollBehavior(AppBarLayout appBarLayout, RecyclerView recyclerView) {
        mAppBarLayout = appBarLayout;
        mRecyclerView = recyclerView;
    }

    public boolean isRecylerViewScrollable(RecyclerView recyclerView) {
        int recyclerViewHeight = recyclerView.getHeight(); // Height includes RecyclerView plus AppBarLayout at same level
        int appCompatHeight = mAppBarLayout != null ? mAppBarLayout.getHeight() : 0;
        recyclerViewHeight -= appCompatHeight;

        return recyclerView.computeVerticalScrollRange() > recyclerViewHeight;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        if (isRecylerViewScrollable(mRecyclerView)) {
            return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes);
        }
        return false;
    }

}
