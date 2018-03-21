package tw.android.test.activity.video;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.hermes.test.R;

import butterknife.BindView;
import tw.android.test.base.BaseSimpleActivity;

public class VideoActivity extends BaseSimpleActivity {

    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.videoView)
    VideoView mVideoView;

    int position = 0;
    MediaController mMediaController;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, VideoActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_video);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        if (mMediaController == null) {
            mMediaController = new MediaController(this);

            mVideoView.setMediaController(mMediaController);
        }

        try {
            // video file.
            mVideoView.setVideoPath("http://techslides.com/demos/sample-videos/small.mp4");
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        mVideoView.requestFocus();

        // When the video file ready for playback.
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                mVideoView.seekTo(position);
                if (position == 0) {
                    mVideoView.start();
                }

                // When video Screen change size.
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

                        // Re-Set the mVideoView that acts as the anchor for the MediaController
                        mMediaController.setAnchorView(mVideoView);
                    }
                });
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.v(TAG, "onConfigurationChanged");
        setContentView();
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(TAG, "ORIENTATION_LANDSCAPE");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d(TAG, "ORIENTATION_PORTRAIT");
        } else {
            Log.d(TAG, "ORIENTATION_UNDEFINED");
        }
    }
}
