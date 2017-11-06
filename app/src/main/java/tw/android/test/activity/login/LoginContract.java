package tw.android.test.activity.login;

import tw.android.test.base.BaseMVPView;
import tw.android.test.base.BasePresenter;

/**
 * Created by hermes.hsieh on 2017/11/6.
 */

public interface LoginContract {

    interface View extends BaseMVPView<Presenter> {

        void setStateText(String text);

        void setAccountInput(String text);

        void setPasswordInput(String text);

        void setAccountInputEnable(boolean enable);

        void setPasswordInputEnable(boolean enable);
    }

    interface Presenter extends BasePresenter {

        void onClickLoginButton(String account, String password);

        void onClickLogoutButton();

        void onClickRegisterButton();

        void onClickForgetPasswordButton();
    }
}
