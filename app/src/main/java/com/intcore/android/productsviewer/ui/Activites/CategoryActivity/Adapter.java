package com.intcore.android.productsviewer.ui.Activites.CategoryActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.intcore.android.productsviewer.R;
import com.intcore.android.productsviewer.data.CategoryData.Data;
import com.intcore.android.productsviewer.data.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.CtegoryProductsViewHolder> {

    private List<Data> mProducts;
    private Context mContext;

    public Adapter(List<Data> products, Context context) {
        mProducts = products;
        mContext = context;
    }

    @NonNull
    @Override
    public CtegoryProductsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CtegoryProductsViewHolder(
                LayoutInflater
                        .from(mContext)
                        .inflate(R.layout.main_activity_recycler_view_element,
                                viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CtegoryProductsViewHolder ctegoryProductsViewHolder, int i) {
        ctegoryProductsViewHolder.bind(mProducts.get(i));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    class CtegoryProductsViewHolder extends RecyclerView.ViewHolder{
        private TextView mPriceTextView;
        private TextView mNameTextView;
        private ImageView mImageView;

        public CtegoryProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            mPriceTextView = itemView.findViewById(R.id.product_price_text_view);
            mNameTextView = itemView.findViewById(R.id.product_name_text_view);
            mImageView = itemView.findViewById(R.id.product_image_view);
        }

        public void bind(Data product){
            String name= product.getNameEn();
            if(name.length() > 15){
                name = product.getNameEn().substring(0,15)+"...";
            }
            Picasso.get()
                    .load("https://e-commerce-dev.intcore.net/"+ product.getDefaultImage())
                    .into(mImageView);
            mNameTextView.setText(name);
            mPriceTextView.setText("$ "+product.getPrice());
        }
    }

}
