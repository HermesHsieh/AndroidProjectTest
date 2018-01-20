package tw.android.test.ui.form;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.widget.FrameLayout;

import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditTextItem extends FormView.ItemView {

    public enum INPUT_TYPE {
        NORMAL(0),
        EMAIL(1),
        NUMBER(2),
        PASSWORD(3);

        private int value;

        INPUT_TYPE(int value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    @BindView(R.id.layout)
    FrameLayout layout;

    @BindView(R.id.input_layout)
    TextInputLayout inputLayout;
    @BindView(R.id.edit_text)
    TextInputEditText editText;

    private Context context;
    private INPUT_TYPE type = INPUT_TYPE.NORMAL;

    public void setInputType(INPUT_TYPE type) {
        if (editText == null) {
            return;
        }

        switch (type) {
            default:
            case NORMAL:
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case EMAIL:
                editText.setInputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS |
                        InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            case NUMBER:
                editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;

            case PASSWORD:
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.view_item_edittext;
    }

    public EditTextItem(@NonNull Context context) {
        super(context);
        ButterKnife.bind(this, getView());
        this.context = context;
        setInputType(this.type);
    }

    public EditTextItem(Context context, final String hintStr) {
        this(context);

        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        inputLayout.setHint(hintStr);
    }

    public EditTextItem(Context context, final String hintStr, @DrawableRes int rightIcon) {
        this(context, hintStr);

        editText.setCompoundDrawablePadding((int) context.getResources().getDimension(R.dimen.padding_16));
        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context, rightIcon), null);
    }

    public void setPasswordToggleEnabled(boolean enable) {
        inputLayout.setPasswordVisibilityToggleEnabled(enable);
    }

    public void setCounterToggleEnable(boolean enable, int maxLength) {
        inputLayout.setCounterEnabled(enable);
        inputLayout.setCounterMaxLength(maxLength);
    }

    public void isPicker(boolean isPicker) {
        if (isPicker) {
            editText.setFocusable(false);
            editText.setClickable(true);
        }
    }

    public void setLayoutColor(int layoutColor) {
        layout.setBackgroundColor(ContextCompat.getColor(context, layoutColor));
    }

    public void setError(String errorMsg) {
        inputLayout.setError(errorMsg);
    }

    public TextInputEditText getEditText() {
        return editText;
    }

    public TextInputLayout getInputLayout() {
        return inputLayout;
    }

    public void setEditTextStr(String editTextStr) {
        editText.setText(editTextStr);
    }

    public String getEditTextStr() {
        return editText.getText().toString();
    }
}
