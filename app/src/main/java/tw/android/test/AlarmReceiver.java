package tw.android.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


/**
 * Created by hermes on 2017/9/2.
 */

public class AlarmReceiver extends BroadcastReceiver {

    private final static String TAG = AlarmReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG, "onReceive ---*");
//        if (intent.getAction() == ACTION_REPEAT_ALARM) {
//            Log.d(TAG, "ACTION_REPEAT_ALARM");
//        }
    }

}
