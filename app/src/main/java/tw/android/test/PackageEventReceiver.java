package tw.android.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by hermes on 2017/6/6.
 */

public class PackageEventReceiver extends BroadcastReceiver {

    private final static String TAG = PackageEventReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive ---*");
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
//            Toast.makeText(context, "<<安装成功>>" + packageName, Toast.LENGTH_LONG).show();
        }
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
//            Toast.makeText(context, "<<移除成功>>" + packageName, Toast.LENGTH_LONG).show();
        }
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
//            Toast.makeText(context, "<<替換成功>>" + packageName, Toast.LENGTH_LONG).show();
        }
    }
}