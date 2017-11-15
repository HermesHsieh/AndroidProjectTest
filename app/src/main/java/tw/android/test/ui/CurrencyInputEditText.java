package tw.android.test.ui;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Created by hermes.hsieh on 2017/11/14.
 */

public class CurrencyInputEditText extends TextInputEditText {

    private final static Integer MAX_FLOAT_NUMBER = 4;

    private final static boolean isEnableClear = false;

    private final static boolean isEnableFormat = false;

    private DecimalFormat dollarFormat;

    private TextWatcher mTextWatcher;

    private OnAfterTextChangedListener onAfterTextChangedListener;

    public interface OnAfterTextChangedListener {
        void onAfterTextChanged(String text);
    }

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
        mTextWatcher = new TextWatcher() {
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
                    int sec = getSelectionStart();
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
                                if (isEnableFormat) {
                                    setText(dollarFormat.format(n) + "." + mFloat);
                                }
                                if (onAfterTextChangedListener != null) {
                                    onAfterTextChangedListener.onAfterTextChanged(getCurrency().toString());
                                }
                            }
                        } else {
                            Number n = dollarFormat.parse(mInputString);
                            if (isEnableFormat) {
                                setText(dollarFormat.format(n));
                            }
                            if (onAfterTextChangedListener != null) {
                                onAfterTextChangedListener.onAfterTextChanged(getCurrency().toString());
                            }
                        }
                        if (isEnableFormat) {
                            setSelection(sec);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (isEnableClear) {
                        setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.ic_notification_clear_all, 0);
                    }
                } else {
                    if (isEnableClear) {
                        setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }
                }
                addTextChangedListener(this);
            }
        };

        addTextChangedListener(mTextWatcher);

        if (isEnableClear) {
            setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        if (event.getX() >= v.getWidth() * 0.85) {
                            setText("");
                        }
                    }
                    return false;
                }
            });
        }
    }

    public void setOnAfterTextChangedListener(OnAfterTextChangedListener listener) {
        this.onAfterTextChangedListener = listener;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.addTextChangedListener(null);
        if (isEnableClear) {
            setOnTouchListener(null);
        }
        mTextWatcher = null;
        onAfterTextChangedListener = null;
    }

    public Double getCurrency() {
        return Double.valueOf(getText().toString().replace(String.valueOf(dollarFormat.getDecimalFormatSymbols().getGroupingSeparator()), ""));
    }

    public TextWatcher getTextWatcher() {
        return mTextWatcher;
    }

}
