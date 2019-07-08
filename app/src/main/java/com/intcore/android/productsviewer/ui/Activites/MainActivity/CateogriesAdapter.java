package com.intcore.android.productsviewer.ui.Activites.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.intcore.android.productsviewer.MyApp;
import com.intcore.android.productsviewer.R;
import com.intcore.android.productsviewer.data.Category;
import com.intcore.android.productsviewer.ui.Activites.CategoryActivity.CategoryActivity;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CateogriesAdapter extends RecyclerView.Adapter<CateogriesAdapter.CategoryViewHolder> {

    private List<Category> mCategories;
    private Context mContext;

    public CateogriesAdapter(List<Category> categories, Context context) {
        mCategories = categories;
        mContext = context;
    }

    @NonNull
    @Override
    public CateogriesAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(
                R.layout.categories_recycler_view_element,
                viewGroup,
                false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.bind(mCategories.get(i));
    }



    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mCateogryImage;
        private TextView mNameTextView;
        private Category mCategory;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mCateogryImage =  itemView.findViewById(R.id.category_image_view);
            mNameTextView = itemView.findViewById(R.id.category_name_text_view);
        }

        public void bind(Category category){
            mNameTextView.setText(category.getName_en());
            MyApp.getInstance().getPicasso().load("https://e-commerce-dev.intcore.net/"+category.getImage())
                    .into(mCateogryImage);
            mCategory = category;
        }

        @Override
        public void onClick(View view) {
            View v = (View)((AppCompatActivity) mContext).findViewById(R.id.category_image_view);
            mContext.startActivity(CategoryActivity.createIntent(mContext, mCategory),
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                            ( (AppCompatActivity)mContext),
                                v,
                                "categoryImage")
                            .toBundle() );
        }
    }
}
