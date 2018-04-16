package com.git4u.interview.module_user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/module/user/login")
public class LoginActivity extends AppCompatActivity {

    @Autowired(name = "keyString")
    String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_login);
        setTitle("module_user activity");

        ARouter.getInstance().inject(this);

//        String string = getIntent().getStringExtra("keyString");

        TextView textView = (TextView) findViewById(R.id.text_view);
        if (string != null) {
            textView.append("\n" + string);
        }
    }
}
