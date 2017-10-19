package tw.android.test.activity.search;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.hermes.test.R;

import tw.android.test.base.BaseSimpleActivity;

/**
 * Created by hermes.hsieh on 2017/9/16.
 */

public class SearchActivity extends BaseSimpleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d("searchView", "from intent search text : " + query);
        }
    }

    @Override
    protected void initView() {
        setTitle(this.getClass().getSimpleName());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public boolean onSearchRequested() {
        return super.onSearchRequested();
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
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final MenuItem searchItem = menu.findItem(R.id.menu_action_search);
//        MenuItemCompat.expandActionView(searchItem);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        if (searchView != null) {
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.d("searchView", "Submit text : " + query);
                    searchView.setQuery("", false);
                    searchView.clearFocus();
//                    searchView.onActionViewCollapsed();
                    searchItem.collapseActionView();
                    SearchResultsActivity.launch(SearchActivity.this, query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.d("searchView", "Change text : " + newText);
                    return false;
                }
            });

//            MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
//                @Override
//                public boolean onMenuItemActionExpand(MenuItem item) {
//                    return false;
//                }
//
//                @Override
//                public boolean onMenuItemActionCollapse(MenuItem item) {
//                    finish();
//                    return false;
//                }
//            });
        } else {
            Log.e("searchView", "searchView == null");
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
