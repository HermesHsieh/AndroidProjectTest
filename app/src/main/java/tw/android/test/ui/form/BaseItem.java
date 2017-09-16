package tw.android.test.ui.form;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by hermes on 2017/9/14.
 */

public abstract class BaseItem extends View {

    private final View rootView;

    BaseItem(Context context) {
        super(context);
        rootView = LayoutInflater.from(context).inflate(getLayoutId(), null);
    }

    protected abstract int getLayoutId();

    public View getView() {
        return rootView;
    }
}
