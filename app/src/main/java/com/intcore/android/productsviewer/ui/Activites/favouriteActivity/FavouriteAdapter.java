package com.intcore.android.productsviewer.ui.Activites.favouriteActivity;

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
import com.intcore.android.productsviewer.data.CategoryData.Products;
import com.intcore.android.productsviewer.data.Message;
import com.intcore.android.productsviewer.networking.favourite.FavouriteBody;
import com.intcore.android.productsviewer.presenters.FavouritePresenter.FavouritePresenter;
import com.intcore.android.productsviewer.presenters.FavouritePresenter.FavouriteView;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {

    private List<Data> mProducts;
    private Context mContext;
    private String mApiToken;

    public FavouriteAdapter(List<Data> products, Context context) {
        mProducts = products;
        mContext = context;
    }

    public String getApiToken() {
        return mApiToken;
    }

    public void setApiToken(String apiToken) {
        mApiToken = apiToken;
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.favourite_recycler_view_element,viewGroup, false);
        return new FavouriteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder favouriteViewHolder, int i) {
        favouriteViewHolder.bind(mProducts.get(i));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    class FavouriteViewHolder extends RecyclerView.ViewHolder implements FavouriteView {

        private ImageView mProductImageView;
        private TextView mProductNameTextView;
        private TextView mProductPriceTextView;
        private ImageView mRemoveProductImageView;
        private FavouritePresenter mPresenter;
        private Data mData;

        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            mProductImageView = itemView.findViewById(R.id.favourite_product_image_view);
            mProductNameTextView = itemView.findViewById(R.id.favourite_product_name_text_view);
            mProductPriceTextView = itemView.findViewById(R.id.favourite_product_price_text_view);
            mRemoveProductImageView = itemView.findViewById(R.id.delete_favourite_product_image_view);
            mPresenter = new FavouritePresenter(this);
        }

        public void bind(final Data data){
            Picasso.get()
                    .load("https://e-commerce-dev.intcore.net/"+data.getDefaultImage())
                    .into(mProductImageView);
            mProductNameTextView.setText(data.getNameEn());
            mProductPriceTextView.setText("$ "+data.getPrice());
            mRemoveProductImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPresenter.addToFavourite(new FavouriteBody(
                            getApiToken(),
                            String.valueOf(data.getId())
                    ));
                    mData = data;

                }
            });
        }

        @Override
        public void onAddToFavouriteFinished(Message message) {
            mProducts.remove(mData);
            FavouriteAdapter.this.notifyDataSetChanged();
        }

        @Override
        public void onAddToFavouriteFailed(String errorMessage) {

        }
    }
}
