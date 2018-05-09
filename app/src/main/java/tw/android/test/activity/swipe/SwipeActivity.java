package tw.android.test.activity.swipe;

import android.app.Activity;
import android.content.Intent;

import com.example.hermes.test.R;

import tw.android.test.base.BaseSimpleActivity;

public class SwipeActivity extends BaseSimpleActivity {

    //    Toolbar toolbar;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, SwipeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_swipe);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_swipe);
//        setDragEdge(SwipeBackLayout.DragEdge.LEFT);
//
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//        }
//    }
}
