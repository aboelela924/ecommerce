package com.intcore.android.productsviewer.ui.Activites.MainActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.intcore.android.productsviewer.MyApp;
import com.intcore.android.productsviewer.R;
import com.intcore.android.productsviewer.data.Message;
import com.intcore.android.productsviewer.data.Product;
import com.intcore.android.productsviewer.data.user.User;
import com.intcore.android.productsviewer.networking.favourite.FavouriteBody;
import com.intcore.android.productsviewer.presenters.FavouritePresenter.FavouritePresenter;
import com.intcore.android.productsviewer.presenters.FavouritePresenter.FavouriteView;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ProductViewHolder> {

    private Context mContext;
    private List<Product> mProducts;
    private User mUser;

    public MainActivityAdapter(Context context, List<Product> products) {
        mContext = context;
        mProducts = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new ProductViewHolder(inflater
                .inflate(R.layout.main_activity_recycler_view_element,viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        productViewHolder.bind(mProducts.get(i));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements FavouriteView {

        private TextView mNameTextView;
        private TextView mPriceTextView;
        private ImageView mProductImageView;
        private ImageView mLikeImageView;
        private AVLoadingIndicatorView mLoadingIndicatorView;
        private FavouritePresenter mPresenter;



        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            mPresenter = new FavouritePresenter(this);
            mNameTextView = itemView.findViewById(R.id.product_name_text_view);
            mPriceTextView = itemView.findViewById(R.id.product_price_text_view);
            mProductImageView = itemView.findViewById(R.id.product_image_view);
            mLoadingIndicatorView = itemView.findViewById(R.id.recycler_view_element_like);
            mLikeImageView = itemView.findViewById(R.id.like_image_view);

            mPresenter = new FavouritePresenter(this);

        }

        public void bind(final Product product){
            String name = product.getName_en();
            if(name.length() > 15){
                name = name.substring(0,15)+"...";
            }
            mNameTextView.setText(name);
            mPriceTextView.setText("$"+product.getPrice());
            MyApp.getInstance().getPicasso().load("https://e-commerce-dev.intcore.net/"+product.getImages().get(0).getImage())
                    .resize(520,520)
                    .into(mProductImageView);
            if(product.isIs_fav()){
                mLikeImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.checked));
            }
            else {
                mLikeImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.unchecked));
            }

            mLikeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mUser == null){
                        Toast.makeText(mContext, "Please, Login first", Toast.LENGTH_LONG).show();
                    }else{
                        mPresenter.addToFavourite(new FavouriteBody(
                                mUser.getApiToken(),
                                product.getId()
                        ));
                        mLoadingIndicatorView.setVisibility(View.VISIBLE);
                        mLoadingIndicatorView.show();
                    }

                }
            });
        }


        @Override
        public void onAddToFavouriteFinished(Message message) {

            mLoadingIndicatorView.hide();
            mLoadingIndicatorView.setVisibility(View.INVISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    MyApp.getInstance().getMyDatabase().productDao().updaeFavourite();
                }
            }).start();
            if(areDrawablesIdentical(mLikeImageView.getDrawable(),
                    mContext.getResources().getDrawable(R.drawable.checked))){

                mLikeImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.unchecked));

            }else{

                mLikeImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.checked));

            }
        }

        @Override
        public void onAddToFavouriteFailed(String errorMessage) {

        }

        private boolean areDrawablesIdentical(Drawable drawableA, Drawable drawableB) {
            Drawable.ConstantState stateA = drawableA.getConstantState();
            Drawable.ConstantState stateB = drawableB.getConstantState();
            // If the constant state is identical, they are using the same drawable resource.
            // However, the opposite is not necessarily true.
            return (stateA != null && stateB != null && stateA.equals(stateB))
                    || getBitmap(drawableA).sameAs(getBitmap(drawableB));
        }

        private Bitmap getBitmap(Drawable drawable) {
            Bitmap result;
            if (drawable instanceof BitmapDrawable) {
                result = ((BitmapDrawable) drawable).getBitmap();
            } else {
                int width = drawable.getIntrinsicWidth();
                int height = drawable.getIntrinsicHeight();
                // Some drawables have no intrinsic width - e.g. solid colours.
                if (width <= 0) {
                    width = 1;
                }
                if (height <= 0) {
                    height = 1;
                }

                result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(result);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);
            }
            return result;
        }
    }

}


