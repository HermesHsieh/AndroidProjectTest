package tw.android.test.activity.dragger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hermes.test.R;
import com.github.ppamorim.dragger.DraggerPosition;
import com.github.ppamorim.dragger.DraggerView;

public class DraggerActivity extends AppCompatActivity {

    DraggerView mRootDraggerView;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, DraggerActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragger);

        mRootDraggerView = (DraggerView) findViewById(R.id.root_drag_view);
        mRootDraggerView.setDraggerPosition(DraggerPosition.RIGHT);
    }
}
