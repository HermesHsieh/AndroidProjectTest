package tw.android.test.activity.image;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hermes.test.R;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.OnClick;
import tw.android.test.base.BaseSimpleActivity;
import tw.android.test.data.RechargeData;
import tw.android.test.utils.Utility;

/**
 * Created by hermes.hsieh on 2018/1/29.
 */

public class ImageActivity extends BaseSimpleActivity {

    public final String TAG = getClass().getSimpleName();

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.save)
    Button save;

    Bitmap mBitmap;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_image);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        new LoadingImageAsyncTask().execute(RechargeData.mImage);
    }

    @OnClick(R.id.save)
    public void onViewClicked() {
        if (isExternalStorageWritable()) {
            File file = getAlbumStorageDir("smartmall");
            if (file != null) {
                saveBitmap(file, "recharge_qrcode.jpg", mBitmap, new SaveBitmapListener() {
                    @Override
                    public void onSaveStatus(boolean status) {
                        if (status) {
                            Toast.makeText(ImageActivity.this, "Save succeed!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ImageActivity.this, "Save failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Log.e(TAG, "Cannot create smartmall file...");
            }
        } else {
            Log.e(TAG, "isExternalStorageWritable = false!");
        }
    }

    /**
     * Get the directory for the user's public pictures directory.
     *
     * @param albumName
     * @return the file. If directory not created, return null.
     */
    public File getAlbumStorageDir(String albumName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
            return null;
        }
        return file;
    }

    /**
     * Checks if external storage is available for read and write
     **/
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if external storage is available to at least read
     **/
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    class LoadingImageAsyncTask extends AsyncTask<String, String, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            return Utility.getBitmap(strings[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                mBitmap = bitmap;
                image.setImageBitmap(bitmap);
                Log.d(TAG, "Bitmap size : " + bitmap.getWidth() + "*" + bitmap.getHeight());
            }
        }
    }

    private void saveBitmap(File saveDir, String fileName, Bitmap bitmap, final SaveBitmapListener listener) {
        boolean status;
        File file = new File(saveDir, fileName);

        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        }

        listener.onSaveStatus(status);
    }

    public interface SaveBitmapListener {
        void onSaveStatus(boolean status);
    }

}
