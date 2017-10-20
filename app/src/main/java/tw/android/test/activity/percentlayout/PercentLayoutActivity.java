package tw.android.test.activity.percentlayout;

import android.app.Activity;
import android.content.Intent;

import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.OnClick;
import tw.android.test.base.BaseSimpleActivity;
import tw.android.test.ui.ShowDialog;
import tw.android.test.ui.form.FormView;
import tw.android.test.ui.form.LabelItem;

/**
 * Created by hermes on 2017/10/18.
 */

public class PercentLayoutActivity extends BaseSimpleActivity {

    @BindView(R.id.form_view)
    FormView mFormView;

    FormView.Adapter mAdapter;

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
    }

    ShowDialog mShowDialog;

    @OnClick(R.id.button)
    public void onClickShowDialogButton() {
        if(mShowDialog==null)
        mShowDialog = new ShowDialog.Builder(this).build();
        mShowDialog.show();
    }
}
