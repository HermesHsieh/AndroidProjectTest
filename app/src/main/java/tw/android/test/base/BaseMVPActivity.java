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
        initPresenter();
        if (mPresenter != null) {
            mPresenter.onCreate();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    public abstract void initPresenter();
}
