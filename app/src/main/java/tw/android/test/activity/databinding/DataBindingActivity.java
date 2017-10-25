package tw.android.test.activity.databinding;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.widget.Toast;

import com.example.hermes.test.R;
import com.example.hermes.test.databinding.ActivityDataBindBinding;

import tw.android.test.base.BaseSimpleActivity;
import tw.android.test.data.User;

/**
 * Created by hermes on 2017/10/23.
 */

public class DataBindingActivity extends BaseSimpleActivity {

    private ActivityDataBindBinding mMBinding;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, DataBindingActivity.class);
        activity.startActivity(intent);
    }

    public void onClickShowToastButton(User user) {
        Log.d("testtt", "click");
        Toast.makeText(this, "Click button! " + user.firstName + " " + user.lastName, Toast.LENGTH_SHORT).show();
        mMBinding.setUser(new User("Hahaha", "YOyoyoyoyo"));
    }

    @Override
    protected void initView() {
        mMBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind);
        mMBinding.setHandlers(this);
        User user = new User("Test", "User");
        mMBinding.setUser(user);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setContentView() {

    }
}
