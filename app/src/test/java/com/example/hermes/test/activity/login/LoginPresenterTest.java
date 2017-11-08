package com.example.hermes.test.activity.login;

import android.content.Context;
import android.content.pm.ApplicationInfo;

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
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hermes.hsieh on 2017/11/6.
 */

public class LoginPresenterTest {

    @Mock
    private LoginActivity mLoginView;

    @Mock
    Context mMockContext;

    @Mock
    ApplicationInfo mApplicationInfo;

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
//        when(mMockContext.getApplicationInfo()).thenReturn(mApplicationInfo);
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

//        doReturn("請輸入帳號")
                when(mMockContext.getString(R.string.e_input_empty, mMockContext.getString(R.string.account)))
                .thenReturn("請輸入帳號");

        String result = mMockContext.getString(R.string.e_input_empty, mMockContext.getString(R.string.account));
        assertEquals(result, "請輸入帳號");

//        verify(mLoginView).showMsgDialog(result);
//        assertEquals("請輸入帳號", mMockContext.getString(R.string.e_input_empty, mMockContext.getString(account)));
//        verify(mLoginView).showMsgDialog("請輸入帳號");
    }

    @Test
    public void userOnClickLogin_accountInputNull_showsErrorUi() {
        mLoginPresenter = new LoginPresenter(mLoginView);

        mLoginPresenter.onClickLoginButton(null, TEST_PASSWORD);
        verify(mLoginView).showMsgDialog(mLoginView.getString(R.string.e_input_empty, mLoginView.getString(R.string.account)));
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

//        when(mMockContext.getString(R.string.e_input_empty))
//                .thenReturn("請輸入%s");
//        when(mMockContext.getString(R.string.password))
//                .thenReturn("密碼");

        verify(mLoginView).showMsgDialog(mMockContext.getString(R.string.e_input_empty, mMockContext.getString(R.string.password)));
    }

    @Test
    public void userOnClickLogin_Input_All_Empty_showsErrorUi() {
        mLoginPresenter = new LoginPresenter(mLoginView);

        mLoginPresenter.onClickLoginButton("", "");
        verify(mLoginView).showMsgDialog(mLoginView.getString(R.string.e_input_empty, mLoginView.getString(account)));
        verify(mLoginView, never()).showMsgDialog(mLoginView.getString(R.string.e_input_empty, mLoginView.getString(R.string.password)));
        verify(mLoginView, never()).setStateText(anyString());
        verify(mLoginView, never()).setAccountInputEnable(false);
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
