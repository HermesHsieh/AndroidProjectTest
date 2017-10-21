package tw.android.test.ui.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

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
            builder.formView.setAdapter(builder.adapter);
            builder.show();
        }
    }

    public void dismiss() {
        if (builder != null) {
            builder.dismiss();
        }
    }

    public static class Builder {

        protected final Context context;

        protected MaterialDialog dialog;

        protected FormView formView;

        protected FormView.Adapter adapter;

        public Builder(Context context) {
            this.context = context;
        }

        public final Context getContext() {
            return context;
        }

        @UiThread
        public ShowDialog build() {
            adapter = new FormView.Adapter();
            dialog = new MaterialDialog.Builder(context)
                    .customView(R.layout.dialog_show_list, false)
                    .positiveText(R.string.g_ok)
                    .autoDismiss(false)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            adapter.add(new LabelItem(context, "Title add", "Value add"));
                            adapter.notifyDataSetChanged();
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

            formView = (FormView) dialog.getCustomView().findViewById(R.id.form_view);

            adapter.add(new LabelItem(context, "Title1", "Value1"));
            adapter.add(new LabelItem(context, "Title2", "Value2"));
            adapter.add(new LabelItem(context, "Title3", "Value3"));

            return new ShowDialog(this);
        }

        @UiThread
        public ShowDialog show() {
            ShowDialog dialog = build();
            dialog.show();
            return dialog;
        }

        public void dismiss() {
            if (dialog != null) {
                dialog.dismiss();
            }
        }

    }
}
