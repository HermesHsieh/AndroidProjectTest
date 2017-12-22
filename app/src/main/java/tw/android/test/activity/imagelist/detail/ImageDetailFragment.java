package tw.android.test.activity.imagelist.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hermes.hsieh on 2017/12/22.
 */

public class ImageDetailFragment extends Fragment {

    private final static String NAME = "name";

    @BindView(R.id.image_view)
    ImageView mImageView;

    @BindView(R.id.image_name)
    TextView mImageName;

    String mName;

    public static ImageDetailFragment newInstance(String name) {
        ImageDetailFragment f = new ImageDetailFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mName = args.getString(NAME, "");
        }

        getActivity().setTitle(mName);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mImageName.setText(mName);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}