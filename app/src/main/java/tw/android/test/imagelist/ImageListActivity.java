package tw.android.test.imagelist;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hermes.test.R;

import java.util.ArrayList;

import tw.android.test.PackageEventReceiver;
import tw.android.test.ui.FormView;
import tw.android.test.ui.SpinnerItem;


/**
 * Created by hermes on 2017/9/12.
 */

public class ImageListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ImageListAdapter mAdapter;
    private FormView.Adapter mFormAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        findView();

//        mPackageEventReceiver = new PackageEventReceiver();
//        mBroadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
//                    String packageName = intent.getData().getSchemeSpecificPart();
//                    Toast.makeText(context, "m安装成功 " + packageName, Toast.LENGTH_LONG).show();
//                }
//                if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
//                    String packageName = intent.getData().getSchemeSpecificPart();
//                    Toast.makeText(context, "m移除成功 " + packageName, Toast.LENGTH_LONG).show();
//                }
//                if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
//                    String packageName = intent.getData().getSchemeSpecificPart();
//                    Toast.makeText(context, "m替換成功 " + packageName, Toast.LENGTH_LONG).show();
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
        mFormAdapter = new FormView.Adapter();
        mFormAdapter.add(new SpinnerItem(this, "Title", new ArrayList<String>()));
        mFormAdapter.add(new SpinnerItem(this, "Title", new ArrayList<String>()));

        ((SpinnerItem) mFormAdapter.getItemView(0)).getSelectedValue();
    }

    private PackageEventReceiver mPackageEventReceiver;
    private BroadcastReceiver mBroadcastReceiver;

    private void findView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new ImageListAdapter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

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
}
