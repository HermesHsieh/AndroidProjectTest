package tw.android.test.activity.home;

import dagger.Component;

/**
 * Created by hermes.hsieh on 2017/10/2.
 */
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
