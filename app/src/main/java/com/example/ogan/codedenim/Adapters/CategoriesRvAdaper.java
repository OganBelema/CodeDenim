package com.example.ogan.codedenim.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ogan.codedenim.Courses.CoursesByCategories;
import com.example.ogan.codedenim.Gson.Categories.CategoriesApus;

import com.example.ogan.codedenim.R;

import java.util.ArrayList;

/**
 * Created by ogan on 8/26/17.
 */

public class CategoriesRvAdaper extends RecyclerView.Adapter<CategoriesRvAdaper.CategoriesVH> {

    Context context;
    private ArrayList<CategoriesApus> categoryResult;

    public CategoriesRvAdaper(ArrayList<CategoriesApus> myDataset) {

        categoryResult = myDataset;
    }

    @Override
    public CategoriesRvAdaper.CategoriesVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.my_categories_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        CategoriesVH vh = new CategoriesVH(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(CategoriesRvAdaper.CategoriesVH holder, final int position) {


        final String categoryName = categoryResult.get(position).getCategoryName();
        holder.categoryName.setText(categoryName);


        /* Picasso.with(context).
                load(courseResult.get(position).getSnippet().getThumbnails().getMedium().getUrl()).
                into(holder.courseImage); */

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int categoryId = categoryResult.get(position).getCourseCategoryId();

                Intent intent = new Intent(context, CoursesByCategories.class);
                intent.putExtra("CategoryId", categoryId);
                intent.putExtra("categoryName", categoryName);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryResult.size();
    }

    //View Holders

    public class CategoriesVH extends RecyclerView.ViewHolder{

        TextView categoryName;
        CardView cardView;




        public CategoriesVH(View view){
            super(view);
            context = view.getContext();
            categoryName = (TextView) view.findViewById(R.id.categoriesTxt);
            cardView = (CardView) view.findViewById(R.id.categories_card);

        }
    }


}
