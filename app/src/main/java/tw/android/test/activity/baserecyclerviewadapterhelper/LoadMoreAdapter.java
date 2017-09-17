package tw.android.test.activity.baserecyclerviewadapterhelper;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hermes.test.R;

import tw.android.test.data.DataServer;

/**
 * Created by hermes.hsieh on 2017/9/17.
 */

public class LoadMoreAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public LoadMoreAdapter(int count) {
        super(R.layout.item_string, DataServer.getStringDataList(count));
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.textView, item);
    }

}
