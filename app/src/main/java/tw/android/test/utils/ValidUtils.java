package tw.android.test.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

public class ValidUtils {

    public static boolean isValidPattern(Pattern pattern, String string) {
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return pattern.matcher(string).matches();
    }

    protected static class Patterns {
        private static final String EMAIL_PATTERN =
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+";
        private static final Pattern EMAIL_ADDRESS = Pattern.compile(EMAIL_PATTERN);

        private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-z]+)(?=.*[0-9]+)(?!(?=.*[^a-zA-Z0-9]+))(?=\\S+$).{6,20}$";
        private static final Pattern PASSWORD = Pattern.compile(PASSWORD_PATTERN);
    }
}
