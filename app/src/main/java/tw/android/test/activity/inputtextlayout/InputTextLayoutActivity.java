package tw.android.test.activity.inputtextlayout;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;

import com.example.hermes.test.R;

import butterknife.BindView;
import tw.android.test.base.BaseSimpleActivity;
import tw.android.test.ui.form.EditTextItem;
import tw.android.test.ui.form.FormView;

/**
 * Created by hermes.hsieh on 2018/1/20.
 */

public class InputTextLayoutActivity extends BaseSimpleActivity {

    @BindView(R.id.form_view)
    FormView formView;
    @BindView(R.id.btn)
    Button nextStep;

    FormView.Adapter adapter;

    EditTextItem userNameItem;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, InputTextLayoutActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_input_text_layout);
    }

    @Override
    protected void initView() {
        setTitle(this.getClass().getSimpleName());

        adapter = new FormView.Adapter();

        userNameItem = new EditTextItem(this, getString(R.string.register_p_account));
        adapter.add(userNameItem);
        formView.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }
}
