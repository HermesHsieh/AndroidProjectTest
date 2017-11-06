package tw.android.test.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by hermes.hsieh on 2017/11/6.
 */

public abstract class BaseMVPActivity<T extends BasePresenter> extends BaseSimpleActivity implements BaseMVPView<T> {
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
