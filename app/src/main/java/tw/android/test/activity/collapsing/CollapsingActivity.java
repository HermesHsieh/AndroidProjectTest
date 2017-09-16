package tw.android.test.activity.collapsing;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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

//    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int initContentView() {
        return R.layout.activity_collapsing;
    }

    @Override
    protected void initView() {

        mAdapter = new ListDataAdapter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }

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
        mAdapter.onCreateData(50);
        mAdapter.notifyDataSetChanged();
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


    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, CollapsingActivity.class);
        activity.startActivity(intent);
    }
}
