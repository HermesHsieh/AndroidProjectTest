package tw.android.test.activity.theme;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.OnClick;
import tw.android.test.base.BaseSimpleActivity;


public class ThemeActivity extends BaseSimpleActivity {

    @BindView(R.id.tabs)
    TabLayout tabLayout;

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
