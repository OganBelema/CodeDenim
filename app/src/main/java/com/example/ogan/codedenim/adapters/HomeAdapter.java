package com.example.ogan.codedenim.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ogan.codedenim.LearningPathDetailActivity;
import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.adapters.HomeAdapter.HomeViewHolder;
import com.example.ogan.codedenim.gson.LearningPath;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by belema on 10/29/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder>{

    private Context context;
    private ArrayList<LearningPath> categoryResult;

    public HomeAdapter(ArrayList<LearningPath> myDataset) {

        categoryResult = myDataset;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.home_category_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(final HomeViewHolder holder, int position) {
        try {
            final String categoryName = categoryResult.get(holder.getAdapterPosition()).getCategoryName();
            final String categoryPrice = String.valueOf(categoryResult.get(holder.getAdapterPosition()).getAmount());
            final String categoryDesc = categoryResult.get(holder.getAdapterPosition()).getCategoryDescription();
            final String imageLocation = "http://codedenim.azurewebsites.net/MaterialUpload/" + categoryResult.get(position).getImageLocation();

            holder.textView.setText(categoryName);

            Picasso.with(context).load(imageLocation).into(holder.imageView);
            /*Picasso.with(context).load(imageLocation).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(Palette palette) {
                            Palette.Swatch swatch = palette.getDarkMutedSwatch();
                            if (swatch != null) {
                                //setting color of toolbar and title text
                                holder.textView.setTextColor(swatch.getTitleTextColor());
                            }
                        }
                    });
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });*/

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int categoryId = categoryResult.get(holder.getAdapterPosition()).getCourseCategoryId();
                    String studentType = categoryResult.get(holder.getAdapterPosition()).getStudentType();

                    //Intent intent = new Intent(context, CoursesByCategories.class);
                    Intent intent = new Intent(context, LearningPathDetailActivity.class);
                    intent.putExtra("CategoryId", categoryId);
                    intent.putExtra("CategoryName", categoryName);
                    intent.putExtra("ImageLocation", imageLocation);
                    intent.putExtra("CategoryDescription", categoryDesc);
                    intent.putExtra("CategoryPrice", categoryPrice);
                    intent.putExtra("StudentType", studentType);
                    context.startActivity(intent);

                }
            });
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return categoryResult.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;

        HomeViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            imageView = itemView.findViewById(R.id.home_category_image);
            textView = itemView.findViewById(R.id.home_category_name);
        }
    }
}
