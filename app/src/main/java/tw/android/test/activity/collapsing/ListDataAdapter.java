package tw.android.test.activity.collapsing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hermes.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hermes on 2017/9/12.
 */

public class ListDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;

    private List<String> mData = new ArrayList<>();

    public ListDataAdapter(Context context) {
        mContext = context;
        onCreateData(30);
    }

    private void onCreateData(int count) {
        mData.clear();
        for (int i = 0; i < count; i++) {
            mData.add("[ " + i + " ] ListView Data");
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_string, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mTextView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
