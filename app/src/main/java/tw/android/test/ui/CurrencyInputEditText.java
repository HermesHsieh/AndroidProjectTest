package tw.android.test.ui;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Created by hermes.hsieh on 2017/11/14.
 */

public class CurrencyInputEditText extends TextInputEditText {

    private final static String TAG = CurrencyInputEditText.class.getSimpleName();

    private final static Integer MAX_FLOAT_NUMBER = 4;

    private DecimalFormat dollarFormat;

    public CurrencyInputEditText(Context context) {
        super(context);
        init();
    }

    public CurrencyInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CurrencyInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        dollarFormat = new DecimalFormat("#,###");
        setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                removeTextChangedListener(this);
                if (!TextUtils.isEmpty(s)) {
                    try {
                        String mInputString = s.toString().replace(String.valueOf(dollarFormat.getDecimalFormatSymbols().getGroupingSeparator()), "");
                        String mInteger = null;
                        String mFloat = null;
                        if (mInputString.contains(".")) {
                            String[] split = mInputString.split("\\.");
                            if (split.length > 0) {
                                mInteger = split[0];
                            }

                            if (split.length > 1) {
                                mFloat = split[1];

                                if (mFloat.length() >= MAX_FLOAT_NUMBER) {
                                    mFloat = mFloat.substring(0, MAX_FLOAT_NUMBER);
                                } else {
                                    mFloat = mFloat.substring(0, mFloat.length());
                                }
                            }

                            Number n = dollarFormat.parse(mInteger);

                            if (mInteger != null && mFloat != null) {
                                setText(dollarFormat.format(n) + "." + mFloat);
                            }
                        } else {
                            Number n = dollarFormat.parse(mInputString);
                            setText(dollarFormat.format(n));
                        }
                        setSelection(getText().length());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.ic_notification_clear_all, 0);
                } else {
                    setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
                addTextChangedListener(this);
            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.d(TAG, "View : " + v.getWidth() + "," + v.getHeight());
                    Log.d(TAG, "Touch Down : " + event.getX() + "," + event.getY());
                    if (event.getX() >= v.getWidth() * 0.85) {
                        setText("");
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.addTextChangedListener(null);
    }

    public Double getCurrency() {
        return Double.valueOf(getText().toString());
    }
}
