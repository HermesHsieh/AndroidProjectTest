package tw.android.test.utils;

import com.example.hermes.test.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * Created by hermes.hsieh on 2017/12/28.
 */

public class LogUtils {
    public static boolean isDebug = BuildConfig.DEBUG;

    private static final String TAG = BuildConfig.APPLICATION_ID;

    public static void e(String tag, Object o) {
        if (isDebug) {
            Logger.e(tag, o);
        }
    }

    public static void e(Object o) {
        LogUtils.e(TAG, o);
    }

    public static void w(String tag, Object o) {
        if (isDebug) {
            Logger.w(tag, o);
        }
    }

    public static void w(Object o) {
        LogUtils.w(TAG, o);
    }

    public static void d(String msg) {
        if (isDebug) {
            Logger.d(msg);
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Logger.i(msg);
        }
    }
}
