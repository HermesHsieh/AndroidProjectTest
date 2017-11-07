package tw.android.test.base;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by hermes.hsieh on 2017/11/6.
 */

public interface BaseView {

    void showLoading();

    void dismissLoading();

    void showMsgDialog(String msg);

//    void showMsgDialog(String title, String msg);

    void showMsgDialog(String msg, MaterialDialog.SingleButtonCallback callback);

    void showMsgDialog(String title, String msg, MaterialDialog.SingleButtonCallback callback);

    void showErrorFinishDialog(String msg);

}
