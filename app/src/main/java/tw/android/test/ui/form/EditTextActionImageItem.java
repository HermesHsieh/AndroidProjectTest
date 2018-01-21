package tw.android.test.ui.form;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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

    public ProgressBar getProgressBar() {
        return mProgressBar;
    }

    public ImageView getActionImage() {
        return mActionImage;
    }

    public void setActionImageDrawable(int drawable) {
        mActionImage.setImageResource(drawable);
    }

}
