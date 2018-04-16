package tw.android.test;

import android.support.multidex.BuildConfig;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by hermes.hsieh on 2017/11/24.
 */

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
