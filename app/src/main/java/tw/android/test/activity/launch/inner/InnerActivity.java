package tw.android.test.activity.launch.inner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.hermes.test.R;

import tw.android.test.activity.launch.LaunchActivity;

public class InnerActivity extends AppCompatActivity {
    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, InnerActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner);
        setTitle(getClass().getSimpleName());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent parentIntent = NavUtils.getParentActivityIntent(this);

                if (parentIntent != null) {
                    Log.d(LaunchActivity.TAG, "parentIntent: " + parentIntent.toString());
                    Log.d(LaunchActivity.TAG, "shouldUpRecreateTask: " + NavUtils.shouldUpRecreateTask(this, parentIntent));
                } else {
                    Log.d(LaunchActivity.TAG, "parentIntent: null");
                }

                Log.d(LaunchActivity.TAG, "isTaskRoot: " + isTaskRoot());
                if ((parentIntent != null && NavUtils.shouldUpRecreateTask(this, parentIntent))
                        || isTaskRoot()) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(parentIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    Log.d(LaunchActivity.TAG, "onBackPressed");
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    onBackPressed();
                }

//                NavUtils.navigateUpFromSameTask(this);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }
}
