package tw.android.test.activity.imagelist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hermes.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import tw.android.test.activity.imagelist.detail.ImageDetailActivity;

/**
 * Created by hermes.hsieh on 2017/12/22.
 */

public class ImageListFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    ImageListAdapter mAdapter;

    public static ImageListFragment newInstance() {
        return new ImageListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new ImageListAdapter(getActivity());

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnClickListener((v, position, name) -> {
//            ImageDetailActivity.launch(getActivity(), name);
            ImageDetailActivity.launch(
                    getActivity(),
                    name,
                    v.findViewById(R.id.image_view),
                    v.findViewById(R.id.image_name)
            );
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter.onCreateData(50);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}