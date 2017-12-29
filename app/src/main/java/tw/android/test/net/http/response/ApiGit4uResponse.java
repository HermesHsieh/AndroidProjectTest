package tw.android.test.net.http.response;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by hermes.hsieh on 2017/12/28.
 */
@Data
public class ApiGit4uResponse<T, K> {

    @SerializedName("status")
    private K status = null;

    @SerializedName("data")
    private T data = null;

    @SerializedName("message")
    private String message = null;
}
