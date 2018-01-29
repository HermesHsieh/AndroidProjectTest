package tw.android.test.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

/**
 * Created by hermes.hsieh on 2018/1/29.
 */

public class Utility {

    /**
     * Get a bitmap by using Base64 decoder.
     *
     * @param imageString the image string is encode to base64.
     * @return get a bitmap, the default is return null.
     */
    public static Bitmap getBitmap(String imageString) {
        if (!TextUtils.isEmpty(imageString)) {
            byte[] decodedString = Base64.decode(imageString, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        }
        return null;
    }
}
