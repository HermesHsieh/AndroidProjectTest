package tw.android.test.activity.image;

import android.widget.Button;
import android.widget.ImageView;

import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.OnClick;
import tw.android.test.base.BaseSimpleActivity;

/**
 * Created by hermes.hsieh on 2018/1/29.
 */

public class ImageActivity extends BaseSimpleActivity {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.save)
    Button save;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_image);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.save)
    public void onViewClicked() {
    }
}
