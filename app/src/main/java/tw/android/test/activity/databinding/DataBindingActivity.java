package tw.android.test.activity.databinding;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import com.example.hermes.test.R;
import com.example.hermes.test.databinding.ActivityDataBindBinding;

import tw.android.test.base.BaseSimpleActivity;
import tw.android.test.data.User;

/**
 * Created by hermes on 2017/10/23.
 */

public class DataBindingActivity extends BaseSimpleActivity {

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, DataBindingActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void initView() {
        ActivityDataBindBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind);
        User user = new User("Test", "User");
        binding.setUser(user);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setContentView() {

    }
}
