package tw.android.test.activity.login;

import tw.android.test.base.BasePresenterImpl;

import static tw.android.test.activity.login.LoginActivity.FORGET_PASSWORD_ACTION;
import static tw.android.test.activity.login.LoginActivity.LOGIN_ACTION;
import static tw.android.test.activity.login.LoginActivity.REGISTER_ACTION;

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

    @Override
    public void onClickLoginButton() {
        mView.setStateText(LOGIN_ACTION);
    }

    @Override
    public void onClickRegisterButton() {
        mView.setStateText(REGISTER_ACTION);
    }

    @Override
    public void onClickForgetPasswordButton() {
        mView.setStateText(FORGET_PASSWORD_ACTION);
    }
}
