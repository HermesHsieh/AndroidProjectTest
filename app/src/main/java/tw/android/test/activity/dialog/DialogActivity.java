package tw.android.test.activity.dialog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.text.InputType;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hermes.test.R;

import butterknife.OnClick;
import tw.android.test.base.BaseSimpleActivity;
import tw.android.test.ui.dialog.ShowDialog;

/**
 * Created by hermes.hsieh on 2017/12/2.
 */

public class DialogActivity extends BaseSimpleActivity {

    public final String TAG = getClass().getSimpleName();

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, DialogActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_dialog);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    ShowDialog mShowDialog;

    @OnClick(R.id.show_dialog)
    public void onClickShowDialogButton() {
        if (mShowDialog == null)
            mShowDialog = new ShowDialog.Builder(this).build();
        mShowDialog.show();
    }

    @OnClick(R.id.transparent_dialog)
    public void onClickTransparentDialogButton() {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .customView(R.layout.dialog_transparent, false)
                .build();
        dialog.show();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    @OnClick(R.id.input_dialog)
    public void onClickInputDialog() {
        new MaterialDialog.Builder(this)
                .title(R.string.input_title)
                .content(R.string.input_content)
                .inputType(InputType.TYPE_CLASS_NUMBER)
                .negativeText("Cancel")
                .input(R.string.input_hint, R.string.input_pre_fill, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        // Do something
                        Log.d(TAG, "onInput : " + input);
                    }
                }).show();
    }
}
