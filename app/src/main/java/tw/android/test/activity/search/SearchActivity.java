package tw.android.test.activity.search;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.hermes.test.R;

import tw.android.test.BaseSimpleActivity;

/**
 * Created by hermes.hsieh on 2017/9/16.
 */

public class SearchActivity extends BaseSimpleActivity {
    @Override
    protected int initContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        setTitle(this.getClass().getSimpleName());
    }

    @Override
    protected void initData() {

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
                (SearchView) menu.findItem(R.id.menu_action_search).getActionView();
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
//            case R.id.menu_action_search:
//                TransitionManager.beginDelayedTransition((ViewGroup) this.findViewById(R.id.toolbar));
//                MenuItemCompat.expandActionView(item);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, SearchActivity.class);
        activity.startActivity(intent);
    }
}
