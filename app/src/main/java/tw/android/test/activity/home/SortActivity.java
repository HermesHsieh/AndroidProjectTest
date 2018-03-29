package tw.android.test.activity.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import tw.android.test.base.BaseSimpleActivity;

/**
 * Created by hermes.hsieh on 2017/10/23.
 */

public class SortActivity extends BaseSimpleActivity {

    private static final String _ID = "_Id";

    public static class Builder {
        private Integer mId;

        private Builder() {
        }

        public static Builder getBuilder() {
            Builder builder = new Builder();
            return builder;
        }

        public Builder setId(Integer id) {
            mId = id;
            return this;
        }

        public Intent build(Context context) {
            Intent intent = new Intent(context, SortActivity.class);
            intent.putExtra(_ID, mId);
            return intent;
        }
    }

    public static void launch(Activity activity, Intent intent) {
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setContentView() {

    }

}
