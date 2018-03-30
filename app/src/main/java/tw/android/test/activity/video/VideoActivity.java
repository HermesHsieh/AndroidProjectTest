package tw.android.test.activity.video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.VideoView;

import com.example.hermes.test.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import tw.android.test.base.BaseSimpleActivity;

public class VideoActivity extends BaseSimpleActivity {

    public static final String VIDEO_TITLE = "Wowza Demo RTSP Video";
    private final String TAG = getClass().getSimpleName();

    //    @BindView(R.id.videoView)
    VideoView mVideoView;

    int position = 0;
    MediaController mMediaController;

    // http://techslides.com/demos/sample-videos/small.mp4
    String videoUrl = "http://clips.vorwaerts-gmbh.de/VfE_html5.mp4";
    String videoUrl2 = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov";
    String videoUrl3 = "http://www.wowza.com/_h264/BigBuckBunny_175k.mov";
    String videoUrl4 = "rtsp://192.168.0.35:8554/SampleVideo.mkv";
    String videoUrl5 = "rtsp://192.168.0.68:8554/MainconceptLogo_AVC_ES_320x240_420.264";


    @BindView(R.id.videoPlayer)
    JZVideoPlayerStandard jzVideoPlayer;

    @BindView(R.id.spinner_url)
    Spinner mSpinnerUrl;

    List<String> mVideoUrlList;

    ArrayAdapter mAdapter;

    int mSelectedPosition = 0;

    private static final String PREFS = "configs";
    private static final String SELECTED_URL_INDEX = "selected_url_index";

    SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        sp = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        mSelectedPosition = sp.getInt(SELECTED_URL_INDEX, 0);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause");
        super.onPause();
        JZVideoPlayerStandard.releaseAllVideos();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, VideoActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void setContentView() {
        Log.d(TAG, "setContentView");
        setContentView(R.layout.activity_video);
    }

    @Override
    protected void initView() {
        Log.d(TAG, "initView");

//        jzVideoPlayer.onEvent(JZUserAction.ON_ENTER_FULLSCREEN);
//        jzVideoPlayer.startWindowFullscreen();

        // This method will crash...
//        JZVideoPlayerStandard.startFullscreen(this, JZVideoPlayerStandard.class, videoUrl4, VIDEO_TITLE);

        mVideoUrlList = createVideoUrlArray();

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mVideoUrlList);

        mSpinnerUrl.setAdapter(mAdapter);

        mSpinnerUrl.setSelection(mSelectedPosition);

        mSpinnerUrl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemSelected position: " + position);

                mSelectedPosition = position;

                sp.edit().putInt(SELECTED_URL_INDEX, position).apply();

                updateVideoPlayerSetup();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void updateVideoPlayerSetup() {
        if (jzVideoPlayer.isCurrentPlay()) {
            jzVideoPlayer.release();
        }

        jzVideoPlayer.setUp(mVideoUrlList.get(mSelectedPosition), JZVideoPlayer.SCREEN_WINDOW_NORMAL, VIDEO_TITLE);
        jzVideoPlayer.thumbImageView.setImageResource(R.mipmap.android_developer);
    }

    @Override
    protected void initData() {
        Log.d(TAG, "initData");
//        if (mMediaController == null) {
//            mMediaController = new MediaController(this);
//
//            mVideoView.setMediaController(mMediaController);
//        }
//
//        try {
//            // video file.
//            mVideoView.setVideoPath(videoUrl2);
//        } catch (Exception e) {
//            Log.e("Error", e.getMessage());
//            e.printStackTrace();
//        }
//
//        mVideoView.requestFocus();
//
//        // When the video file ready for playback.
//        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            public void onPrepared(MediaPlayer mediaPlayer) {
//                mVideoView.seekTo(position);
//                if (position == 0) {
//                    mVideoView.start();
//                }
//
//                // When video Screen change size.
//                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
//                    @Override
//                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
//
//                        // Re-Set the mVideoView that acts as the anchor for the MediaController
//                        mMediaController.setAnchorView(mVideoView);
//                    }
//                });
//            }
//        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfigurationChanged");
//        setContentView();
//        initView();
//        initData();
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Log.d(TAG, "ORIENTATION_LANDSCAPE");
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            if (getSupportActionBar() != null) {
//                getSupportActionBar().isShowing();
//                getSupportActionBar().hide();
//            }
//
//            View decorView = getWindow().getDecorView();
//            // Hide both the navigation bar and the status bar.
//            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
//            // a general rule, you should design your app to hide the status bar whenever you
//            // hide the navigation bar.
//            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
//            decorView.setSystemUiVisibility(uiOptions);
//        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            Log.d(TAG, "ORIENTATION_PORTRAIT");
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            if (getSupportActionBar() != null) {
//                getSupportActionBar().show();
//            }
//        } else {
//            Log.d(TAG, "ORIENTATION_UNDEFINED");
//        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayerStandard.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    private List<String> createVideoUrlArray() {
        List<String> list = new ArrayList<>();
        list.add("http://clips.vorwaerts-gmbh.de/VfE_html5.mp4");
        list.add("rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov");
        list.add("http://www.wowza.com/_h264/BigBuckBunny_175k.mov");
        list.add("rtsp://192.168.0.35:8554/SampleVideo.mkv");
        list.add("rtsp://192.168.0.68:8554/MainconceptLogo_AVC_ES_320x240_420.264");
        return list;
    }
}
