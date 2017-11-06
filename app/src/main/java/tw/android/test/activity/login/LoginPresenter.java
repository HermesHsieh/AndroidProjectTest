package tw.android.test.activity.login;

import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hermes.test.R;
import com.google.common.base.Strings;

import tw.android.test.base.BasePresenterImpl;

import static tw.android.test.activity.login.LoginActivity.FORGET_PASSWORD_ACTION;
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
    public void onClickLoginButton(String account, String password) {
        if (Strings.isNullOrEmpty(account)) {
            mView.showMsgDialog(mContext.getString(R.string.e_input_empty, mContext.getString(R.string.account)));
            return;
        }

        if (Strings.isNullOrEmpty(password)) {
            mView.showMsgDialog(mContext.getString(R.string.e_input_empty, mContext.getString(R.string.password)));
            return;
        }

        mView.setStateText(account);

        mView.showMsgDialog(mContext.getString(R.string.state_login_succeed));

        mView.setAccountInputEnable(false);

        mView.setPasswordInputEnable(false);
    }

    @Override
    public void onClickLogoutButton() {
        mView.showMsgDialog(mContext.getString(R.string.ask_logout), new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                mView.setAccountInput("");
                mView.setPasswordInput("");
                mView.setStateText(mContext.getString(R.string.state_logout_succeed));
                mView.setAccountInputEnable(true);
                mView.setPasswordInputEnable(true);
            }
        });
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
