package tw.android.test.activity.home;

import dagger.Module;

/**
 * Created by hermes.hsieh on 2017/10/2.
 */
@Module
public class MainModule {
    private MainActivity mMainActivity;

    public MainModule(MainActivity activity) {
        this.mMainActivity = activity;
    }
}
