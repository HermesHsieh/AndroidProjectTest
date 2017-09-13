package tw.android.test.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hermes.test.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hermes.hsieh on 2017/9/13.
 */

public class SpinnerItem extends FormView.ItemView {

    private Context mContext;

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.spinner)
    Spinner mSpinner;

    private ArrayAdapter<String> mAdapter;

    public SpinnerItem(@NonNull Context context) {
        super(context);
        ButterKnife.bind(this, getView());
        this.mContext = context;
    }

    public SpinnerItem(@NonNull Context context, String title) {
        this(context);
        setTitle(title);
    }

    public SpinnerItem(@NonNull Context context, String title, List<String> list) {
        this(context, title);
        setSpinnerData(list);
    }

    public SpinnerItem(@NonNull Context context, String title, List<String> list, OnSpinnerSelectedListener listener) {
        this(context, title, list);
        setSpinnerData(list);
        setSpinnerListener(listener);
    }

    @Override
    public int getLayoutId() {
        return R.layout.view_item_new_spinner;
    }

    public void setTitle(String title) {
        mTitle.setText(!TextUtils.isEmpty(title) ? title : "");
    }

    public void setSpinnerData(List<String> list) {
        mAdapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_list_item_1, list);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);
    }

    public void setSpinnerListener(final OnSpinnerSelectedListener listener) {
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listener.onItemSelected(position, mAdapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public interface OnSpinnerSelectedListener {
        void onItemSelected(int position, String value);
    }

}
