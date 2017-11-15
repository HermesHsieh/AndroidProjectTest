package tw.android.test.utils;

import android.content.res.Resources;

import com.example.hermes.test.R;

/**
 * Created by hermes.hsieh on 2017/11/15.
 */

public class ResourceManager {
    private Resources mResources;

    public ResourceManager(Resources resources) {
        mResources = resources;
    }

    public String getString(int res) {
        return mResources.getString(res);
    }

    public String getAccount() {
        return mResources.getString(R.string.account);
    }

    public String getPassword() {
        return mResources.getString(R.string.password);
    }
}
