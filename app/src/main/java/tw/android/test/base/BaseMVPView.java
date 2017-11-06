package tw.android.test.base;

/**
 * Created by hermes.hsieh on 2017/11/6.
 */

public interface BaseMVPView<T extends BasePresenter> extends BaseView {

    void setPresenter(T presenter);
}
