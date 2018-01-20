package tw.android.test.ui.form;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditTextActionImageItem extends EditTextItem {

    @BindView(R.id.loading_progress)
    ProgressBar mProgressBar;

    @BindView(R.id.action_image)
    ImageView mActionImage;

    public enum ACTION_STATE {
        NONE(0),
        LOADING(1),
        CORRECT(1),
        ERROR(2);

        private int value;

        ACTION_STATE(int value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.view_item_edittext_actionimage;
    }

    public EditTextActionImageItem(@NonNull Context context) {
        super(context);
        ButterKnife.bind(this, getView());
        this.context = context;
        setInputType(this.type);
    }

    public EditTextActionImageItem(Context context, final String hintStr) {
        this(context);

        inputLayout.setHint(hintStr);
    }

    public EditTextActionImageItem(Context context, final String hintStr, @DrawableRes int rightIcon) {
        this(context, hintStr);

        editText.setCompoundDrawablePadding((int) context.getResources().getDimension(R.dimen.padding_16));
        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(context, rightIcon), null);
    }

    private boolean isEnableActionListener;

    public void setActionListenerToggleEnabled(boolean enable, final OnActionStateListener onActionStateListener) {
        if (editText == null) {
            return;
        }

        isEnableActionListener = enable;
        if (enable && onActionStateListener != null) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (TextUtils.isEmpty(s.toString())) {
                        onActionStateListener.afterTextEmpty();
                    } else {
                        onActionStateListener.afterTextChanged(s);
                    }
                }
            });
        } else {
            editText.addTextChangedListener(null);
        }
    }

    public interface OnActionStateListener {
        void afterTextEmpty();

        void afterTextChanged(Editable s);
    }

    public void onActionStateVisibility(ACTION_STATE state) {
        switch (state) {
            default:
            case NONE:
                mProgressBar.setVisibility(View.GONE);
                mActionImage.setVisibility(View.GONE);
                break;
            case LOADING:
                mProgressBar.setVisibility(View.VISIBLE);
                mActionImage.setVisibility(View.GONE);
                break;
            case CORRECT:
                mProgressBar.setVisibility(View.GONE);
                mActionImage.setVisibility(View.VISIBLE);
                break;
            case ERROR:
                mProgressBar.setVisibility(View.GONE);
                mActionImage.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void setActionImageDrawable(int drawable) {
        mActionImage.setImageResource(drawable);
    }

}
