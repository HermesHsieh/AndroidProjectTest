package tw.android.test.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.OnClick;
import tw.android.test.base.BaseMVPActivity;

/**
 * Created by hermes.hsieh on 2017/11/3.
 */

public class LoginActivity extends BaseMVPActivity<LoginContract.Presenter> implements LoginContract.View {

    public final static String LOGIN_ACTION = "LoginAction";
    public final static String REGISTER_ACTION = "RegisterAction";
    public final static String FORGET_PASSWORD_ACTION = "ForgetPasswordAction";

    private LoginContract.Presenter mPresenter;

    @BindView(R.id.state_text)
    TextView stateText;

    @BindView(R.id.account_input)
    EditText accountInput;

    @BindView(R.id.password_input)
    EditText passwordInput;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initPresenter() {
        new LoginPresenter(this);
    }

    @OnClick(R.id.login_action)
    public void onClickLoginAction() {
        mPresenter.onClickLoginButton(accountInput.getText().toString(), passwordInput.getText().toString());
    }

    @OnClick(R.id.register_action)
    public void onClickRegisterAction() {
        mPresenter.onClickRegisterButton();
    }

    @OnClick(R.id.forget_password_action)
    public void onClickForgetPasswordAction() {
        mPresenter.onClickForgetPasswordButton();
    }

    @OnClick(R.id.logout_action)
    public void onClickLogoutAction() {
        mPresenter.onClickLogoutButton();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setStateText(String text) {
        stateText.setText(text);
    }

    @Override
    public void setAccountInput(String text) {
        accountInput.setText(text);
    }

    @Override
    public void setPasswordInput(String text) {
        passwordInput.setText(text);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }
}
