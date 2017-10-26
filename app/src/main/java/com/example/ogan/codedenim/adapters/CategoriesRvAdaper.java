package com.example.ogan.codedenim.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ogan.codedenim.LearningPathDetailActivity;
import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.gson.LearningPath;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ogan on 8/26/17.
 */

public class CategoriesRvAdaper extends RecyclerView.Adapter<CategoriesRvAdaper.CategoriesVH> {

    private Context context;
    private ArrayList<LearningPath> categoryResult;

    public CategoriesRvAdaper(ArrayList<LearningPath> myDataset) {

        categoryResult = myDataset;
    }

    @Override
    public CategoriesRvAdaper.CategoriesVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoriesVH(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.my_categories_view, parent, false));
    }



    @Override
    public void onBindViewHolder(final CategoriesRvAdaper.CategoriesVH holder, int position) {

        try {
            final String categoryName = categoryResult.get(holder.getAdapterPosition()).getCategoryName();
            final String categoryPrice = String.valueOf(categoryResult.get(holder.getAdapterPosition()).getAmount());
            final String categoryDesc = categoryResult.get(holder.getAdapterPosition()).getCategoryDescription();
            final String imageLocation = "http://codedenim.azurewebsites.net/MaterialUpload/" + categoryResult.get(position).getImageLocation();

            holder.categoryName.setText(categoryName);
            holder.categoryPrice.setText(context.getResources().getString(R.string.money, categoryPrice));
            holder.categoryDesc.setText(categoryDesc);

            Picasso.with(context).load(imageLocation).into(holder.imageView);

            holder.cardView.setOnClickListener(new View.OnClickListener() {
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

    //View Holders

    class CategoriesVH extends RecyclerView.ViewHolder{

        TextView categoryName;
        LinearLayout cardView;
        ImageView imageView;
        TextView categoryPrice;
        TextView categoryDesc;

        CategoriesVH(View view){
            super(view);
            context = view.getContext();
            categoryName = view.findViewById(R.id.categoriesTxt);
            cardView = view.findViewById(R.id.categories_card);
            imageView = view.findViewById(R.id.category_image);
            categoryPrice = view.findViewById(R.id.categoriesPrice);
            categoryDesc = view.findViewById(R.id.categories_description_txt);

        }
    }


}
