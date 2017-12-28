package tw.android.test.activity.rxjava;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.hermes.test.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import tw.android.test.base.BaseSimpleActivity;

/**
 * Created by hermes.hsieh on 2017/12/28.
 */

public class RxJavaActivity extends BaseSimpleActivity {

    public final String TAG = getClass().getSimpleName();

    private List<Integer> mData;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, RxJavaActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_rxjava);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mData.add(i);
        }
    }

    @OnClick({R.id.action1})
    public void onClickAction1Button(View view) {
        Log.v(TAG, "onClickAction1Button : View " + view.getId());

        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        Log.i(TAG, "[create] subscribe thread name :  " + Thread.currentThread().getName());

                        for (int i = 0; i < 1; i++) {
                            if (i == 1) {
                                e.onError(new Exception("i == 2 error!"));
                            }
                            e.onNext(i);
                        }
                        e.onComplete();
                    }
                })
                .map(data -> {
                    Log.i(TAG, "map0 thread name :  " + Thread.currentThread().getName());
                    return data;
                })
                .subscribeOn(Schedulers.io())
                .map(data -> {
                    Log.i(TAG, "map1 thread name :  " + Thread.currentThread().getName());
                    return data;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(data -> {
                    Log.i(TAG, "map2 thread name :  " + Thread.currentThread().getName());
                    return data;
                })
                .observeOn(Schedulers.newThread())
                .map(data -> {
                    Log.i(TAG, "map3 thread name :  " + Thread.currentThread().getName());
                    return String.valueOf(data);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(data -> {
                    Log.i(TAG, "map4 thread name :  " + Thread.currentThread().getName());
                    return String.valueOf(data);
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.i(TAG, "[doOnSubscribe] accept :  " + Thread.currentThread().getName());
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i(TAG, "[doFinally] run :  " + Thread.currentThread().getName());
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i(TAG, "[doOnComplete] run :  " + Thread.currentThread().getName());
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "[doOnError] accept :  " + Thread.currentThread().getName());
                    }
                })
                .subscribeWith(new ResourceObserver<String>() {
                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    Observer subscribeObserver = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.i(TAG, "[subscribe] onSubscribe :  " + Thread.currentThread().getName());

        }

        @Override
        public void onNext(String s) {
            Log.i(TAG, "[subscribe] onNext :  " + Thread.currentThread().getName());

        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "[subscribe] onError :  " + Thread.currentThread().getName());

        }

        @Override
        public void onComplete() {
            Log.i(TAG, "[subscribe] onComplete :  " + Thread.currentThread().getName());

        }
    };

    ResourceObserver resourceObserver = new ResourceObserver<String>() {
        @Override
        public void onNext(String s) {
            Log.i(TAG, "[subscribeWith] onNext :  " + Thread.currentThread().getName());
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "[subscribeWith] onError :  " + Thread.currentThread().getName());
        }

        @Override
        public void onComplete() {
            Log.i(TAG, "[subscribeWith] onComplete :  " + Thread.currentThread().getName());
        }
    };
}
