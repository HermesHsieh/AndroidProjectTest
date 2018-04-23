package tw.android.test.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.hermes.test.R;

public class RedEnvelopDialog extends Dialog {

    TextView envelope;
    boolean isOpen = false;

    public RedEnvelopDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public RedEnvelopDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected RedEnvelopDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dialog_red_envelope);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        envelope = (TextView) findViewById(R.id.envelope);
    }

    public static class Builder {
        private Context mContext;
        private String mMsg;
        private int mBgResId;
        private View.OnClickListener mOnClickListener;

        private Builder(Context context) {
            mContext = context;
        }

        public static Builder getBuilder(Context context) {
            return new Builder(context);
        }

        public Builder setMessage(String string) {
            this.mMsg = string;
            return this;
        }

        public Builder setMessage(int res) {
            this.mMsg = mContext.getString(res);
            return this;
        }

        public Builder setBackgroundResource(int res) {
            this.mBgResId = res;
            return this;
        }

        public Builder setOnClickListener(View.OnClickListener listener) {
            this.mOnClickListener = listener;
            return this;
        }

        public RedEnvelopDialog build() {
            RedEnvelopDialog dialog = new RedEnvelopDialog(mContext);
            if (dialog.envelope != null) {
                if (mBgResId > 0) {
                    dialog.envelope.setBackgroundResource(mBgResId);
                }
                if (mMsg != null) {
                    dialog.envelope.setText(mMsg);
                }
                if (mOnClickListener != null) {
                    dialog.envelope.setOnClickListener(mOnClickListener);
                }
            }
            return dialog;
        }
    }

    public void setOpen(boolean status) {
        isOpen = status;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setMessage(int string) {
        envelope.setText(string);
    }

    public void setMessage(String string) {
        envelope.setText(string);
    }

    public void setBackgroundResource(int resId) {
        envelope.setBackgroundResource(resId);
    }

}
