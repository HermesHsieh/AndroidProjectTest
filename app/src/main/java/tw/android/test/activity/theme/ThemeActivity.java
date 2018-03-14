package tw.android.test.activity.theme;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.ArrayAdapter;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hermes.test.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import tw.android.test.base.BaseSimpleActivity;


public class ThemeActivity extends BaseSimpleActivity {

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.input_layout_2)
    TextInputLayout textInputLayout2;

    @BindView(R.id.spinner)
    AppCompatSpinner spinner;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ThemeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_theme);
    }

    @Override
    protected void initView() {
        tabLayout.addTab(tabLayout.newTab().setText("Tab1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab3"));

        textInputLayout2.setError("Error text!");

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("Item[" + i + "]");
        }

        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                list
        );

        spinner.setAdapter(spinnerAdapter);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.show_dialog_button)
    public void onClickShowDialog() {
        new MaterialDialog.Builder(this)
                .title("Title")
                .content("content")
                .positiveText("OK")
                .negativeText("Cancel")
                .show();
    }
}
