package tw.android.test.activity.login;

import android.content.Context;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hermes.test.R;
import com.google.common.base.Strings;

import tw.android.test.base.BasePresenterImpl;
import tw.android.test.base.BaseSimpleActivity;

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

    public interface TestCallBackTest {
        void onTestCallback(String string);
    }

    @Override
    public void onClickRegisterButton() {
        mView.setStateText(REGISTER_ACTION);
    }

    @Override
    public void onClickForgetPasswordButton() {
        mView.setStateText(FORGET_PASSWORD_ACTION);
    }

    @Override
    public void onClickTestMaterialDesignCallback() {
        mView.testCallback(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(MaterialDialog dialog, DialogAction which) {
                mView.showMsgDialog(mContext.getString(R.string.state_login_succeed));
            }
        });
    }

    @Override
    public void onClickTestCallbackString() {
        mView.testCallback("TEST");
    }

    public class LogoutSubmitCallback implements MaterialDialog.SingleButtonCallback {

        private final LoginContract.View view;
        private final Context context;

        public LogoutSubmitCallback(LoginContract.View view) {
            this.view = view;
            this.context = (BaseSimpleActivity) view;
        }

        @Override
        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
            view.setAccountInput("");
            view.setPasswordInput("");
            view.setStateText(context.getString(R.string.state_logout_succeed));
            view.setAccountInputEnable(true);
            view.setPasswordInputEnable(true);
        }
    }

//    public class LogoutSubmitCallback implements MaterialDialog.SingleButtonCallback {
//
//        private final WeakReference<LoginContract.View> view;
//        private final WeakReference<Context> context;
//
//        public LogoutSubmitCallback(LoginContract.View view) {
//            this.view = new WeakReference<>(view);
//            this.context = new WeakReference<>((BaseSimpleActivity) view);
//        }
//
//        @Override
//        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//            LoginContract.View mView = this.view.get();
//            Context mContext = this.context.get();
//
//            mView.setAccountInput("");
//            mView.setPasswordInput("");
//            mView.setStateText(mContext.getString(R.string.state_logout_succeed));
//            mView.setAccountInputEnable(true);
//            mView.setPasswordInputEnable(true);
//        }
//    }
}
