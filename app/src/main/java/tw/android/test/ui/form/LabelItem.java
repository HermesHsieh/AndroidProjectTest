package tw.android.test.ui.form;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;

/**
 * Created by hermes on 2017/10/20.
 */

public class LabelItem extends FormView.ItemView {

    @Getter
    @BindView(R.id.title)
    TextView title;
    @Getter
    @BindView(R.id.value)
    TextView value;

    @Override
    public int getLayoutId() {
        return R.layout.item_label;
    }

    public LabelItem(@NonNull Context context) {
        super(context);
        ButterKnife.bind(this, getView());
    }

    public LabelItem(@NonNull Context context, String title) {
        this(context);
        setTitle(title);
    }

    public LabelItem(@NonNull Context context, String title, String value) {
        this(context, title);
        setValue(value);
    }

    public LabelItem(@NonNull Context context, int title) {
        this(context);
        setTitle(title);
    }

    public LabelItem(@NonNull Context context, int title, int value) {
        this(context, title);
        setValue(value);
    }

    public void setTitle(int title) {
        this.title.setText(title);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setValue(int value) {
        this.value.setText(value);
    }

    public void setValue(String value) {
        this.value.setText(value);
    }

}
