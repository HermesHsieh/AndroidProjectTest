package tw.android.test.ui;

import android.content.Context;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hermes.test.R;

import tw.android.test.ui.form.FormView;
import tw.android.test.ui.form.LabelItem;

public class ShowDialog {

    private Builder builder;

    ShowDialog(Builder builder) {
        this.builder = builder;
    }

    public void show() {
        if (builder != null) {
            builder.show();
        }
    }

    public void dismiss() {
        if (builder != null) {
            builder.dismiss();
        }
    }

    public static final class Builder {

        private Context context;
        private MaterialDialog dialog;

        private FormView formView;

        private FormView.Adapter adapter;

        public Builder(Context context) {
            this.context = context;
        }

        public ShowDialog build() {

            dialog = new MaterialDialog.Builder(context)
                    .customView(R.layout.dialog_show_list, false)
                    .positiveText(R.string.g_ok)
                    .autoDismiss(false)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                        }
                    })
                    .negativeText(R.string.g_cancel)
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                        }
                    })
                    .build();
            adapter = new FormView.Adapter();

            formView = (FormView) dialog.getCustomView().findViewById(R.id.form_view);

            adapter.add(new LabelItem(context, "Title1", "Value1"));
            adapter.add(new LabelItem(context, "Title2", "Value2"));
            adapter.add(new LabelItem(context, "Title3", "Value3"));

            formView.setAdapter(adapter);

            return new ShowDialog(this);
        }

        public void show() {
            if (dialog != null) {
                dialog.show();
            }
        }

        public void dismiss() {
            if (dialog != null) {
                dialog.dismiss();
            }
        }

    }
}
