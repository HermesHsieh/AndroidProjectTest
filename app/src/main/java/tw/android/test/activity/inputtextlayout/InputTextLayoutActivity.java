package tw.android.test.activity.inputtextlayout;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.OnClick;
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
    EditTextItem passwordItem;

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
        passwordItem = new EditTextItem(this, getString(R.string.register_p_pwd));
        passwordItem.setInputType(EditTextItem.INPUT_TYPE.PASSWORD);
        passwordItem.setPasswordToggleEnabled(true);

        adapter.add(userNameItem);
        adapter.add(emailItem);
        adapter.add(passwordItem);

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

        passwordItem.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    passwordItem.getInputLayout().setHint(getString(R.string.register_pwd));
                } else {
                    if (TextUtils.isEmpty(passwordItem.getEditTextStr())) {
                        passwordItem.getInputLayout().setHint(getString(R.string.register_p_pwd));
                    }
                }
            }
        });

        userNameItem.getEditText().setImeOptions(EditorInfo.IME_ACTION_NEXT);
        emailItem.getEditText().setImeOptions(EditorInfo.IME_ACTION_NEXT);
        passwordItem.getEditText().setImeOptions(EditorInfo.IME_ACTION_DONE);

        userNameItem.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    Toast.makeText(InputTextLayoutActivity.this, "Next user_name", Toast.LENGTH_SHORT).show();
                    // if return true, the cursor will not auto moving to next item.
                }
                return false;
            }
        });

        emailItem.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    Toast.makeText(InputTextLayoutActivity.this, "Next email", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        passwordItem.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Toast.makeText(InputTextLayoutActivity.this, "Done pwd", Toast.LENGTH_SHORT).show();
                    // if return true, the keyboard will not auto close.
                }
                return false;
            }
        });

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.btn)
    public void onClickNextButton() {
        this.finish();
    }
}
