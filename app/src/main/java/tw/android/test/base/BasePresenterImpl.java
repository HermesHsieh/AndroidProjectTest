package tw.android.test.base;

import android.content.Context;

/**
 * Created by hermes.hsieh on 2017/11/6.
 */

public abstract class BasePresenterImpl implements BasePresenter {

    protected BaseMVPView mView;
    protected Context mContext;

    public BasePresenterImpl(BaseMVPView view) {
        this.mView = view;
        this.mContext = (BaseSimpleActivity) view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }
}
