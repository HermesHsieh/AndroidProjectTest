package tw.android.test.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.example.hermes.test.R;

public class ImageViewColored extends android.support.v7.widget.AppCompatImageView {
    private int colorRes;

    public ImageViewColored(Context context) {
        this(context, null);
    }

    public ImageViewColored(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageViewColored(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ImageColored, 0, 0);
            try {
                colorRes = a.getResourceId(R.styleable.ImageColored_color_filter, -1);
                if (colorRes > 0) {
                    setColored(this.colorRes);
                }
            } finally {
                a.recycle();
            }
        }
    }

    public void setColored(@ColorRes int colorRes) {
        setColorFilter(ContextCompat.getColor(getContext(), colorRes));
    }

    public int getColored() {
        return colorRes;
    }
}
