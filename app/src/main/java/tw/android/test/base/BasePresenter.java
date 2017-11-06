package tw.android.test.base;

/**
 * Created by hermes.hsieh on 2017/11/6.
 */

public interface BasePresenter {
    void onCreate();

    void onResume();

    void onPause();

    void onDestroy();
}
