package tw.android.test.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.smartmall2u.smec_android.R;
import com.smartmall2u.smec_android.utils.Utility;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.smartmall.rx.model.ProductEntity;
import io.swagger.smartmall.rx.model.SpecificationEntity;
import io.swagger.smartmall.rx.model.SpecificationValueModel;

/**
 * Created by hermes.hsieh on 2017/9/13.
 */

public class AddProductDialog {
    private Builder builder;

    private AddProductDialog(Builder builder) {
        this.builder = builder;
    }

    public void dismiss() {
        if (builder != null) {
            builder.dialog.dismiss();
        }
    }

    public void show() {
        if (builder != null) {
            builder.dialog.show();
        }
    }

    public int getProductAmount() {
        return builder.mAmount;
    }

    public List<SpecificationValueModel> getSpecValueModelList() {
        List<SpecificationValueModel> list = new ArrayList<>();
        for (int i = 0, j = builder.mFormAdapter.getCount(); i < j; i++) {
            String name = ((SpecSpinnerItem) builder.mFormAdapter.getItemView(i)).getName();
            int value = builder.mProductSpecMap.get(name);
            SpecificationValueModel item = new SpecificationValueModel();
            item.setName(name);
            item.setValue(value);
            list.add(item);
        }
        return list;
    }

    public static final class Builder {
        private final Context mContext;
        ImageView mProductImg;
        TextView mProductName;
        TextView mProductPrice;
        FormView mFormView;
        Button mAddProductSubmit;

        private MaterialDialog dialog;

        private ProductEntity mProductEntity;
        private int mStock;
        private int mBuyLimit;
        private int mAmount = 1;

        private Map<String, Integer> mProductSpecMap = new HashMap<>();

        private View.OnClickListener mAddProductSubmitListener;

        private FormView.Adapter mFormAdapter;

        public Builder setProductEntity(ProductEntity entity) {
            this.mProductEntity = entity;
            return this;
        }

        public Builder setAddProductSubmit(View.OnClickListener listener) {
            mAddProductSubmitListener = listener;
            return this;
        }

        public Builder(Context context) {
            mContext = context;
        }

        public AddProductDialog build() {
            dialog = new MaterialDialog.Builder(mContext)
                    .customView(R.layout.view_item_new_add_product_dialog, true)
                    .build();

            mProductImg = (ImageView) dialog.getCustomView().findViewById(R.id.product_img);
            mProductName = (TextView) dialog.getCustomView().findViewById(R.id.product_name);
            mProductPrice = (TextView) dialog.getCustomView().findViewById(R.id.product_price);
            mFormView = (FormView) dialog.getCustomView().findViewById(R.id.form_view);
            mAddProductSubmit = (Button) dialog.getCustomView().findViewById(R.id.add_product_submit);

            initData();
            mFormAdapter = new FormView.Adapter();

            return new AddProductDialog(this);
        }

        private void initData() {
            mProductName.setText(mProductEntity.getProductName() != null ? mProductEntity.getProductName() : "");
            mProductPrice.setText(Utility.getProductPrice(mContext, mProductEntity.getPriceList()));

            if (mProductEntity.getImageList() != null && mProductEntity.getImageList().size() > 0 && mProductEntity.getImageList().get(0).getImagePath() != null) {
                if (!TextUtils.isEmpty(mProductEntity.getImageList().get(0).getImagePath())) {
                    Picasso.with(mContext)
                            .load(mProductEntity.getImageList().get(0).getImagePath())
                            .fit()
                            .placeholder(R.mipmap.photo)
                            .into(mProductImg);
                }
            }

            mStock = mProductEntity.getStock() != null ? mProductEntity.getStock() : -1;
            mBuyLimit = mProductEntity.getBuyLimit() != null ? mProductEntity.getBuyLimit() : -1;

            mAddProductSubmit.setOnClickListener(mAddProductSubmitListener);

            for (SpecificationEntity specItem : mProductEntity.getSpecificationList()) {
                for (SpecificationValueModel spec : specItem.getSpecificationValues()) {
                    mProductSpecMap.put(
                            spec.getName(),
                            spec.getValue()
                    );
                }
                mFormAdapter.add(new SpecSpinnerItem(
                        mContext,
                        specItem.getSpecificationName(),
                        specItem.getSpecificationValues())
                );
            }
        }
    }
}
