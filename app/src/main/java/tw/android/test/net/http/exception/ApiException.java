package tw.android.test.net.http.exception;

/**
 * Created by hermes.hsieh on 2017/12/28.
 */

public class ApiException extends Exception {

    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(String msg, Object enums) {
        super(msg);
    }
}
