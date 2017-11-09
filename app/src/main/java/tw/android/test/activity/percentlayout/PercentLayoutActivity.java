package tw.android.test.activity.percentlayout;

import android.app.Activity;
import android.content.Intent;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hermes.test.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import tw.android.test.base.BaseSimpleActivity;
import tw.android.test.ui.dialog.ShowDialog;
import tw.android.test.ui.form.FormView;
import tw.android.test.ui.form.LabelItem;

/**
 * Created by hermes on 2017/10/18.
 */

public class PercentLayoutActivity extends BaseSimpleActivity {

    @BindView(R.id.form_view)
    FormView mFormView;

    FormView.Adapter mAdapter;

    List<String> mListData = new ArrayList<>();

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, PercentLayoutActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_percentlayout);
    }

    @Override
    protected void initView() {
        mAdapter = new FormView.Adapter();

        mAdapter.add(new LabelItem(mContext, "Title1", "Value1"));
        mAdapter.add(new LabelItem(mContext, "Title2", "Value2"));
        mAdapter.add(new LabelItem(mContext, "Title3", "Value3"));

        mFormView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {

        new MaterialDialog.Builder(this)
                .customView(R.layout.dialog_demo, false)
                .build();

        for (int i = 0; i < 20; i++) {
            mListData.add("Position[" + i + "]");
        }

    }

    ShowDialog mShowDialog;

    @OnClick(R.id.button)
    public void onClickShowDialogButton() {
//        if (mShowDialog == null)
//            mShowDialog = new ShowDialog.Builder(this).build();
//        mShowDialog.show();

        new MaterialDialog.Builder(this)
                .title("本次重消购买月份")
                .content("您本次重消的总金额为 1000.0000，系统将自动分配至以下月份，以下是分配后的信息，请确认")
                .items(mListData)
                .autoDismiss(true)
                .positiveText(R.string.g_ok)
                .negativeText(R.string.g_cancel)
                .show();
    }
}
