package tw.android.test.utils;

import java.lang.reflect.Field;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Function;
import tw.android.test.net.http.exception.ApiException;
import tw.android.test.net.http.response.ApiGit4uResponse;

/**
 * Created by hermes.hsieh on 2017/12/28.
 */

public class RxUtils {

    public static <T> FlowableTransformer<T, T> handleResponse() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> response) {
                return null;
            }
        };
    }

    public static <T> Boolean checkStatus(T response, Object... enums) {
        try {
            Field status = response.getClass().getDeclaredField("status");
            status.setAccessible(true);
            for (Object successEnum : enums) {
                if (status.get(response).equals(successEnum)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static <T> String getMessage(T response) {
        try {
            Field status = response.getClass().getDeclaredField("message");
            status.setAccessible(true);
            return (String) status.get(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //        public <T> boolean checkResponse(T response, Object... enums) {
//            try {
//                Field status = response.getClass().getDeclaredField("status");
//                status.setAccessible(true);
//                for (Object successEnum : enums) {
//                    if (status.get(response).equals(successEnum)) {
//                        return true;
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return false;
//        }

    public static <T, K> FlowableTransformer<ApiGit4uResponse<T, K>, T> handleResult(final K... ks) {
        return new FlowableTransformer<ApiGit4uResponse<T, K>, T>() {
            @Override
            public Flowable<T> apply(Flowable<ApiGit4uResponse<T, K>> httpResponse) {
                return httpResponse.flatMap(new Function<ApiGit4uResponse<T, K>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(ApiGit4uResponse<T, K> git4uResponse) {
                        for (K k : ks) {
                            if (git4uResponse.getStatus().equals(k)) {
                                return createData(git4uResponse.getData());
                            }
                        }
                        return Flowable.error(new ApiException(git4uResponse.getMessage(), git4uResponse.getStatus().toString()));
                    }
                });
            }
        };
    }

    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
