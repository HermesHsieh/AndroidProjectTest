package tw.android.test.activity.inputtextlayout;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
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
    EditTextItem emailItem;

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
        emailItem = new EditTextItem(this, getString(R.string.register_p_email));
        emailItem.setInputType(EditTextItem.INPUT_TYPE.EMAIL);

        adapter.add(userNameItem);
        adapter.add(emailItem);

        formView.setAdapter(adapter);

        userNameItem.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    userNameItem.getInputLayout().setHint(getString(R.string.register_account));
                } else {
                    if (TextUtils.isEmpty(userNameItem.getEditTextStr())) {
                        userNameItem.getInputLayout().setHint(getString(R.string.register_p_account));
                    }
                }
            }
        });

//        userNameItem.getEditText().addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (!TextUtils.isEmpty(s.toString())) {
//                    userNameItem.getInputLayout().setHint(getString(R.string.register_account));
//                } else {
//                    userNameItem.getInputLayout().setHint(getString(R.string.register_p_account));
//                }
//            }
//        });

        emailItem.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    emailItem.getInputLayout().setHint(getString(R.string.register_email));
                } else {
                    if (TextUtils.isEmpty(emailItem.getEditTextStr())) {
                        emailItem.getInputLayout().setHint(getString(R.string.register_p_email));
                    }
                }
            }
        });

    }

    @Override
    protected void initData() {

    }
}
