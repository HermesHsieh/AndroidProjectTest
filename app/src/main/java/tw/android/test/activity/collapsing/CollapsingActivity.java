package tw.android.test.activity.collapsing;

import android.app.Activity;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.hermes.test.R;

import butterknife.BindView;
import tw.android.test.BaseSimpleActivity;
import tw.android.test.PackageEventReceiver;
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

    @Override
    protected int initContentView() {
        return R.layout.activity_collapsing;
    }

    @Override
    protected void initView() {

        mAdapter = new ListDataAdapter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
//        layoutParams.setBehavior(new AppBarLayoutNoEmptyScrollBehavior(mAppBarLayout, mRecyclerView));

//        mPackageEventReceiver = new PackageEventReceiver();
//        mBroadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
//                    String packageName = intent.getData().getSchemeSpecificPart();
////                    Toast.makeText(context, ">> 安装成功 <<" + packageName, Toast.LENGTH_LONG).show();
//                }
//                if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
//                    String packageName = intent.getData().getSchemeSpecificPart();
////                    Toast.makeText(context, ">> 移除成功 <<" + packageName, Toast.LENGTH_LONG).show();
//                }
//                if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
//                    String packageName = intent.getData().getSchemeSpecificPart();
////                    Toast.makeText(context, ">> 替換成功 <<" + packageName, Toast.LENGTH_LONG).show();
//                }
//            }
//        };
//        IntentFilter mFilter = new IntentFilter();
//        mFilter.addDataScheme("package");
//        mFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
//        mFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);
//        mFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
//        registerReceiver(mBroadcastReceiver, mFilter);
//        registerReceiver(mPackageEventReceiver, mFilter);
    }

    @Override
    protected void initData() {

    }

    private PackageEventReceiver mPackageEventReceiver;
    private BroadcastReceiver mBroadcastReceiver;

    private void findView() {

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
//        collapsingToolbar.setTitle("Expand Title");
    }

    @Override
    protected void onStop() {
        super.onStop();
//        if (mBroadcastReceiver != null)
//            unregisterReceiver(mBroadcastReceiver);
//        if (mPackageEventReceiver != null)
//            unregisterReceiver(mPackageEventReceiver);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
//        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        //Get the ID for the search bar LinearLayout
//        int searchBarId = searchView.getContext().getResources().getIdentifier("android:id/search_bar", null, null);
//        //Get the search bar Linearlayout
//        LinearLayout searchBar = (LinearLayout)
//                searchView.findViewById(searchBarId);
//        //Give the Linearlayout a transition animation.
//        searchBar.setLayoutTransition(new LayoutTransition());

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        if (searchView != null) {
            searchView.setSearchableInfo(
                    searchManager.getSearchableInfo(getComponentName()));
        } else {
            Log.e("testtt", "searchView == null");
        }

        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(final MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_search:
//                TransitionManager.beginDelayedTransition((ViewGroup) this.findViewById(R.id.toolbar));
//                MenuItemCompat.expandActionView(item);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, CollapsingActivity.class);
        activity.startActivity(intent);
    }
}
