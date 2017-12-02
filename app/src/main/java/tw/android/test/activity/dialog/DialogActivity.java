package tw.android.test.activity.dialog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hermes.test.R;

import butterknife.OnClick;
import tw.android.test.base.BaseSimpleActivity;

/**
 * Created by hermes.hsieh on 2017/12/2.
 */

public class DialogActivity extends BaseSimpleActivity {

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

    @OnClick(R.id.show_dialog)
    public void onClickShowDialogButton() {
//        ShowDialog mShowDialog;

//        if (mShowDialog == null)
//            mShowDialog = new ShowDialog.Builder(this).build();
//        mShowDialog.show();

        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .customView(R.layout.dialog_transparent, false)
                .positiveText("testttt")
                .build();
        dialog.show();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    }
}
