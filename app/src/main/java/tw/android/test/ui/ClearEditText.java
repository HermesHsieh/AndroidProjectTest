package tw.android.test.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hermes.hsieh on 2017/11/14.
 */

public class ClearEditText extends LinearLayout {

    @BindView(R.id.currency_input)
    EditText editText;

    @BindView(R.id.action_button)
    ImageView imageView;

    public ClearEditText(Context context) {
        super(context);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.item_clear_edittext, this);
        ButterKnife.bind(this, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        editText = (EditText) findViewById(R.id.currency_input);
//        imageView = (ImageView) findViewById(R.id.action_button);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }
}
