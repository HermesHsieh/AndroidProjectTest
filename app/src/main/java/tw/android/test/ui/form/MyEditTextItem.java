package tw.android.test.ui.form;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hermes on 2017/9/14.
 */

public class MyEditTextItem extends BaseItem {

    @BindView(R.id.edt_title)
    TextView mEdtTitle;
    @BindView(R.id.edt_text)
    EditText mEdtText;

    public MyEditTextItem(Context context) {
        super(context);
        ButterKnife.bind(this, getView());
    }

    public MyEditTextItem(Context context, String title) {
        this(context);
        setTitle(title);
    }

    public MyEditTextItem(Context context, String title, String hintText) {
        this(context, title);
        setHintText(hintText);
    }

    public void setHintText(String hintText) {
        mEdtText.setHint(hintText);
    }

    public void setTitle(String title) {
        mEdtTitle.setText(title);
    }

    @Override
    public int getLayoutId() {
        return R.layout.view_item_edit_text;
    }
}
