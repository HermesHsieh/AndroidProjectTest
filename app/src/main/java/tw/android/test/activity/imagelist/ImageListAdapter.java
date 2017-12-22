package tw.android.test.activity.imagelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hermes.test.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Setter;

/**
 * Created by hermes.hsieh on 2017/12/22.
 */

public class ImageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final String TAG = getClass().getSimpleName();

    private final Context mContext;

    private List<String> mData = new ArrayList<>();

    public ImageListAdapter(Context context) {
        mContext = context;
    }

    public void onCreateData(int count) {
        mData.clear();
        for (int i = 0; i < count; i++) {
            mData.add("[ " + i + " ] ImageList Data");
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder : " + position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.position = position;
        viewHolder.imageName.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        int position;

        @BindView(R.id.image_name)
        TextView imageName;

        @BindView(R.id.image_view)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            Log.i(TAG, "ViewHolder");
            Log.d(TAG, "getAdapterPosition : " + getAdapterPosition());
            Log.d(TAG, "position : " + this.position);

            this.itemView.setOnClickListener(view -> {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                    Log.d(TAG, "click position : " + this.position);
                }
            });
        }
    }

    @Setter
    View.OnClickListener onClickListener;
}
