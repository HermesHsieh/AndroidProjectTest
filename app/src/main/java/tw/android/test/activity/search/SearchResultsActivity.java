package tw.android.test.activity.search;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.hermes.test.R;

import tw.android.test.BaseSimpleActivity;

/**
 * Created by hermes.hsieh on 2017/9/15.
 */

public class SearchResultsActivity extends BaseSimpleActivity {

    public final static String KEY_WORD = "keyWord";

    private String keyWord;
    private SearchView mSearchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        setTitle("Search : " + keyWord);
        handleIntent(getIntent());
    }

    private void getData() {
        if (getIntent() == null) {
            finish();
        }

        keyWord = getIntent().getStringExtra(KEY_WORD);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_search_result);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        final MenuItem searchItem = menu.findItem(R.id.menu_action_search);
        MenuItemCompat.expandActionView(searchItem);
        mSearchView = (SearchView) searchItem.getActionView();
        if (mSearchView != null) {
            Log.d("searchViewResult", "set keyword text : " + keyWord);
            mSearchView.setQuery(keyWord, false);
            mSearchView.clearFocus();
//            searchItem.collapseActionView();
            mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.d("searchViewResult", "Submit text : " + query);
                    mSearchView.clearFocus();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.d("searchViewResult", "Change text : " + newText);
                    return false;
                }
            });
            MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    return false;
                }

                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    finish();
                    return false;
                }
            });
        } else {
            Log.e("searchViewResult", "searchView == null");
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
            Log.d("searchViewResult", "handleIntent : " + query);
            keyWord = query;
//            if (mSearchView != null) {
//                mSearchView.setQuery(query, false);
//                mSearchView.onActionViewCollapsed();
//            }
        }
    }

    public static void launch(Activity activity, String keyword) {
        Intent intent = new Intent(activity, SearchResultsActivity.class);
        intent.putExtra(KEY_WORD, keyword);
        activity.startActivity(intent);
    }
}
