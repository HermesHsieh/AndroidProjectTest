package tw.android.test;

import android.content.BroadcastReceiver;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hermes.test.BuildConfig;
import com.example.hermes.test.R;

import lombok.data.Book;

public class MainActivity extends BaseSimpleActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private final static String BOOK = "LOMBOOK";

    private Button btn_enter;
    private TextView tv_number;
    private EditText edt_number;
    private ListView mListView;

    private CircleView mDrawView;

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btn_enter = (Button) findViewById(R.id.btn_enter);
        tv_number = (TextView) findViewById(R.id.tv_number);
        edt_number = (EditText) findViewById(R.id.edt_number);

        mDrawView = (CircleView) findViewById(R.id.mDrawView);

        tv_number.setText(BuildConfig.VERSION_NAME + ", " + String.valueOf(BuildConfig.VERSION_CODE));

        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cutSize = Integer.valueOf(edt_number.getText().toString());
                mDrawView.setCutSize(cutSize);
            }
        });

        Book book = new Book();
        Book book2 = Book.of(2, 4, "Hello");
        Book book3 = Book.of(4, 4, "");
        new Book.NoArgsExample();
        book.
//        Log.d(BOOK, "Book Id : " + book.getId());
//        Log.d(BOOK, "Book Name : " + book.getName());
//        Log.d(BOOK, "Book Date : " + book.getDate());
//        Log.d(BOOK, "Book Status : " + book.isStatus());
//        Log.d(BOOK, "Book toString : " + book.toString());
    }

    @Override
    protected void initData() {
//        mBroadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Log.d(TAG, "onReceive ---*");
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
//
////        mPackageEventReceiver = new PackageEventReceiver();
//
//        IntentFilter mFilter = new IntentFilter();
//        mFilter.addDataScheme("package");
//        mFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
//        mFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);
//        mFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
////        registerReceiver(mPackageEventReceiver, mFilter);
//        registerReceiver(mBroadcastReceiver, mFilter);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contain, new Fragment());
    }

    private PackageEventReceiver mPackageEventReceiver;
    private BroadcastReceiver mBroadcastReceiver;

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "onStop --->");
//        if (mBroadcastReceiver != null)
//            unregisterReceiver(mBroadcastReceiver);
//        if (mPackageEventReceiver != null)
//            unregisterReceiver(mPackageEventReceiver);
    }
}
