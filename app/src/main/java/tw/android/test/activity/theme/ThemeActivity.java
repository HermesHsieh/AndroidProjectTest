package tw.android.test.activity.theme;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;

import com.example.hermes.test.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import tw.android.test.base.BaseSimpleActivity;
import tw.android.test.data.User;


public class ThemeActivity extends BaseSimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

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
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }

        User.Balances balances = new User.Balances();
        balances.getPoints();
        balances.getRainbowDiamonds();

        tabLayout.addTab(tabLayout.newTab().setText("Tab1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab3"));

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

    boolean eventStatus = false;

    @OnClick(R.id.event)
    public void onClickEvent() {
        if (eventStatus) {
            textInputLayout2.setError(null);
        } else {
            textInputLayout2.setError("error text!!");
        }
        eventStatus = !eventStatus;
    }

//    @OnClick(R.id.show_dialog_button)
//    public void onClickShowDialog() {
//        new MaterialDialog.Builder(this)
//                .title("Title")
//                .content("content")
//                .positiveText("OK")
//                .negativeText("Cancel")
//                .show();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        return true;
    }
}
