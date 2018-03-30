package tw.android.test.activity.launch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.hermes.test.R;


public class LaunchActivity extends AppCompatActivity {

    //    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    ActionBarDrawerToggle toggle;

    public static class Builder {
        private Integer mId;
        private String mName;

        private Builder() {
        }

        public static Builder create() {
            Builder builder = new Builder();
            return builder;
        }

        public Builder setId(Integer id) {
            mId = id;
            return this;
        }

        public Builder setName(String name) {
            mName = name;
            return this;
        }

        public Intent build(Context context) {
            Intent intent = new Intent(context, LaunchActivity.class);
            intent.putExtra("_ID", mId);
            intent.putExtra("_NAME", mName);
            return intent;
        }
    }

    public static void launch(Builder builder) {

    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, LaunchActivity.class);
        activity.startActivity(intent);
    }

    public static void launch(Activity activity, Builder builder) {
        activity.startActivity(builder.build(activity));
    }

    public static void launch(Activity activity, Intent intent) {
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

//        Builder.create().setId(12).setName("Peter");
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this)
//                .setTitle("Title")
//                .setMessage("Msg");
//
//        getIntent().getExtras();
//        getIntent().getBundleExtra("BUILDER");
//        getIntent().getStringExtra("BUILDER");

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
//        toggle.onDrawerOpened();
    }

    void toggle() {
        int drawerLockMode = drawerLayout.getDrawerLockMode(GravityCompat.START);
        if (drawerLayout.isDrawerVisible(GravityCompat.START)
                && (drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_OPEN)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                Intent upIntent = NavUtils.getParentActivityIntent(this);
//
//                if ((upIntent != null && NavUtils.shouldUpRecreateTask(this, upIntent))
//                        || isTaskRoot()) {
//                    // This activity is NOT part of this app's task, so create a new task
//                    // when navigating up, with a synthesized back stack.
//                    TaskStackBuilder.create(this)
//                            // Add all of this activity's parents to the back stack
//                            .addNextIntentWithParentStack(upIntent)
//                            // Navigate up to the closest parent
//                            .startActivities();
//                } else {
//                    // This activity is part of this app's task, so simply
//                    // navigate up to the logical parent activity.
//                    onBackPressed();
//                }

                toggle();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
