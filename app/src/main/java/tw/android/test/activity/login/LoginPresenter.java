package tw.android.test.activity.login;

import tw.android.test.base.BasePresenterImpl;

/**
 * Created by hermes.hsieh on 2017/11/6.
 */

public class LoginPresenter extends BasePresenterImpl implements LoginContract.Presenter {

    protected LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        super(view);
        mView = view;
        mView.setPresenter(this);
    }

}
