package tw.android.test.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hermes.test.R;
import com.jude.swipbackhelper.SwipeBackHelper;

import butterknife.ButterKnife;

/**
 * Created by hermes on 2017/6/17.
 */

public abstract class BaseSimpleActivity extends AppCompatActivity implements BaseView {

    protected Context mContext;

    private ProgressDialog progressDialog;

    protected abstract void setContentView();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.onCreate(this);
        setContentView();
        ButterKnife.bind(this);
        mContext = this;
        initView();
        initData();
        displayHomeButton(true);

        progressDialog = newProgressDialog(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    public void displayHomeButton(boolean isDisplay) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(isDisplay);
            getSupportActionBar().setHomeButtonEnabled(isDisplay);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
        dismissLoading();
        mContext = null;
        progressDialog = null;
    }

    public static ProgressDialog newProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setCancelable(true);
        progressDialog.setMessage(context.getString(R.string.g_loading));

        return progressDialog;
    }

    @Override
    public void showLoading() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void dismissLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showMsgDialog(String msg) {
        new MaterialDialog.Builder(this)
                .autoDismiss(true)
                .content(msg)
                .positiveText(R.string.g_ok)
                .show();
    }

//    @Override
//    public void showMsgDialog(String title, String msg) {
//        new MaterialDialog.Builder(this)
//                .autoDismiss(true)
//                .title(title)
//                .content(msg)
//                .positiveText(R.string.g_ok)
//                .show();
//    }

    @Override
    public void showMsgDialog(String msg, MaterialDialog.SingleButtonCallback callback) {
        new MaterialDialog.Builder(this)
                .autoDismiss(true)
                .content(msg)
                .positiveText(R.string.g_ok)
                .onPositive(callback)
                .show();
    }

    @Override
    public void showMsgDialog(String title, String msg, MaterialDialog.SingleButtonCallback callback) {
        new MaterialDialog.Builder(this)
                .autoDismiss(true)
                .title(title)
                .content(msg)
                .positiveText(R.string.g_ok)
                .onPositive(callback)
                .show();
    }

    @Override
    public void showErrorFinishDialog(String msg) {
        new MaterialDialog.Builder(this)
                .autoDismiss(true)
                .content(msg)
                .positiveText(R.string.g_ok)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        BaseSimpleActivity.this.finish();
                    }
                })
                .show();
    }

}

