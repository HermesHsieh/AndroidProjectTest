package com.example.hermes.test.activity.login;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hermes.test.R;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import tw.android.test.activity.login.LoginActivity;
import tw.android.test.activity.login.LoginPresenter;

import static com.example.hermes.test.R.string.account;
import static org.mockito.Mockito.verify;

/**
 * Created by hermes.hsieh on 2017/11/6.
 */

public class LoginPresenterTest {

    @Mock
    private LoginActivity mLoginView;

    @Mock
    private MaterialDialog.SingleButtonCallback mSingleButtonCallback;

    @Captor
    private ArgumentCaptor<LoginPresenter.LogoutSubmitCallback> mLogoutCaptor;

    private LoginPresenter mLoginPresenter;

    private String TEST_ACCOUNT = "testAccount";
    private String TEST_PASSWORD = "testPassword";

    @Before
    public void setupMocksAndView() {
        MockitoAnnotations.initMocks(this);

//        // The presenter wont't update the view unless it's active.
//        when(mLoginView.isActive()).thenReturn(true);
    }

    @Test
    public void createPresenter_setPresenterToView() {
        // Get a reference to the class under test
        mLoginPresenter = new LoginPresenter(mLoginView);

        // Then the presenter is set to the view
        verify(mLoginView).setPresenter(mLoginPresenter);
    }

    @Test
    public void userOnClickLogin_accountInputEmpty_showsErrorUi() {
        mLoginPresenter = new LoginPresenter(mLoginView);

        mLoginPresenter.onClickLoginButton("", TEST_PASSWORD);
        verify(mLoginView).showMsgDialog(mLoginView.getString(R.string.e_input_empty, mLoginView.getString(account)));
//        verify(mLoginView).showMsgDialog("請輸入帳號");
    }

    @Test
    public void userOnClickLogin_accountInputNull_showsErrorUi() {
        mLoginPresenter = new LoginPresenter(mLoginView);

        mLoginPresenter.onClickLoginButton(null, TEST_PASSWORD);
        verify(mLoginView).showMsgDialog(mLoginView.getString(R.string.e_input_empty, mLoginView.getString(account)));
    }

    @Test
    public void userOnClickLogin_passwordInputEmpty_showsErrorUi() {
        mLoginPresenter = new LoginPresenter(mLoginView);

        mLoginPresenter.onClickLoginButton(TEST_ACCOUNT, "");
        verify(mLoginView).showMsgDialog(mLoginView.getString(R.string.e_input_empty, mLoginView.getString(R.string.password)));
    }

    @Test
    public void userOnClickLogin_passwordInputNull_showsErrorUi() {
        mLoginPresenter = new LoginPresenter(mLoginView);

        mLoginPresenter.onClickLoginButton(TEST_ACCOUNT, null);
        verify(mLoginView).showMsgDialog(mLoginView.getString(R.string.e_input_empty, mLoginView.getString(R.string.password)));
    }

    @Test
    public void userOnClickLogin_showsSucceedUi() {
        mLoginPresenter = new LoginPresenter(mLoginView);

        mLoginPresenter.onClickLoginButton(TEST_ACCOUNT, TEST_PASSWORD);

        verify(mLoginView).setStateText(TEST_ACCOUNT);

        verify(mLoginView).showMsgDialog(mLoginView.getString(R.string.state_login_succeed));

        verify(mLoginView).setAccountInputEnable(false);

        verify(mLoginView).setPasswordInputEnable(false);
    }

    @Test
    public void test_callback_material_dialog_single_button_callback() {
        mLoginPresenter = new LoginPresenter(mLoginView);
        Mockito.doAnswer(invocation -> {
            MaterialDialog.SingleButtonCallback callback = (MaterialDialog.SingleButtonCallback) invocation.getArguments()[0];
            //拿showMsgDialog的第二個參數來轉型
            callback.onClick(null, null);
            return null;
        })
                .when(mLoginView)
                .testCallback(Mockito.any(MaterialDialog.SingleButtonCallback.class));

        mLoginPresenter.onClickTestMaterialDesignCallback();

        Mockito.verify(mLoginView).showMsgDialog(mLoginView.getString(R.string.state_login_succeed));
    }

    @Test
    public void test_callback_input_string() {
        mLoginPresenter = new LoginPresenter(mLoginView);

        mLoginPresenter.onClickTestCallbackString();

        Mockito.verify(mLoginView).testCallback("TEST");
    }

    @Test
    public void userOnClickLogout_succeedClearInputUi() {
        mLoginPresenter = new LoginPresenter(mLoginView);

        Mockito.doAnswer(invocation -> {
            MaterialDialog.SingleButtonCallback callback = (MaterialDialog.SingleButtonCallback) invocation.getArguments()[1];
            //拿showMsgDialog的第二個參數來轉型
            callback.onClick(null, null);
            return null;
        })
                .when(mLoginView)
                .showMsgDialog(Mockito.any(), Mockito.any(MaterialDialog.SingleButtonCallback.class));

        mLoginPresenter.onClickLogoutButton();

        verify(mLoginView).setAccountInput("");
        verify(mLoginView).setPasswordInput("");
        verify(mLoginView).setStateText(mLoginView.getString(R.string.state_logout_succeed));
        verify(mLoginView).setAccountInputEnable(true);
        verify(mLoginView).setPasswordInputEnable(true);

    }
}
