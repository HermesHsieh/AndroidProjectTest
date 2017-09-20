package tw.android.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by hermes.hsieh on 2017/9/20.
 */

public class LocaleChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("LocaleChangedReceiver", "onReceive");
        if (Objects.equals(intent.getAction(), Intent.ACTION_LOCALE_CHANGED)) {
            Log.d("LocaleChangedReceiver", "ACTION_LOCALE_CHANGED");
            String locale = Locale.getDefault().getCountry();
//            Toast.makeText(context, "LOCALE CHANGED to " + locale, Toast.LENGTH_LONG).show();
        }
    }
}
