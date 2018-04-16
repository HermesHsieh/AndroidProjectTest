package tw.android.test.activity.router;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.hermes.test.R;

@Route(path = "/test/activity/router")
public class RouterActivity extends AppCompatActivity {

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, RouterActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router);
    }
}
