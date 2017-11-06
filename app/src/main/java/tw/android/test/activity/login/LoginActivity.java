package tw.android.test.activity.login;

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
        stateText.setText(LOGIN_ACTION);
    }

    @OnClick(R.id.register_action)
    public void onClickRegisterAction() {
        stateText.setText(REGISTER_ACTION);
    }

    @OnClick(R.id.forget_password_action)
    public void onClickForgetPasswordAction() {
        stateText.setText(FORGET_PASSWORD_ACTION);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
