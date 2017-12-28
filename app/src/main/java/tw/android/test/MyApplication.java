package tw.android.test;

import android.support.multidex.MultiDexApplication;

/**
 * Created by hermes.hsieh on 2017/11/24.
 */

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
