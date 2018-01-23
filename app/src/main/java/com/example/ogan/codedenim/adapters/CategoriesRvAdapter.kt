package com.example.ogan.codedenim.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ogan.codedenim.R
import com.example.ogan.codedenim.adapters.CategoriesRvAdapter.CategoriesVH
import com.example.ogan.codedenim.courses.CoursesByCategories
import com.example.ogan.codedenim.gson.LearningPath
import com.squareup.picasso.Picasso
import java.util.*

class CategoriesRvAdapter(private val categoryResult: ArrayList<LearningPath>?) : RecyclerView.Adapter<CategoriesVH>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesVH {
        return CategoriesVH(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.my_categories_view, parent, false))
    }


    override fun onBindViewHolder(holder: CategoriesRvAdapter.CategoriesVH, position: Int) {

        categoryResult?.let {
            val categoryName = categoryResult[holder.adapterPosition].CategoryName
            val categoryPrice = categoryResult[holder.adapterPosition].Amount.toString()
            val categoryDesc = categoryResult[holder.adapterPosition].CategoryDescription
            val imageLocation = "http://codedenim.azurewebsites.net/MaterialUpload/${categoryResult[position].ImageLocation}"

            holder.categoryName.text = categoryName
            holder.categoryPrice.text = context.resources.getString(R.string.money, categoryPrice)
            holder.categoryDesc.text = categoryDesc

            Picasso.with(context).load(imageLocation).into(holder.imageView)

            holder.cardView.setOnClickListener {

                val categoryId = categoryResult[holder.adapterPosition].CourseCategoryId!!

                val mIntent = Intent(context, CoursesByCategories::class.java)
                mIntent.putExtra("CategoryId", categoryId)
                mIntent.putExtra("CategoryName", categoryName)
                context.startActivity(mIntent)



            }
        }

    }

    override fun getItemCount(): Int {
        return categoryResult?.size ?: 0
    }

    //View Holders

    inner class CategoriesVH(view: View) : RecyclerView.ViewHolder(view) {

        var categoryName: TextView
        var cardView: LinearLayout
        var imageView: ImageView
        var categoryPrice: TextView
        var categoryDesc: TextView

        init {
            context = view.context
            categoryName = view.findViewById(R.id.categoriesTxt)
            cardView = view.findViewById(R.id.categories_card)
            imageView = view.findViewById(R.id.category_image)
            categoryPrice = view.findViewById(R.id.categoriesPrice)
            categoryDesc = view.findViewById(R.id.categories_description_txt)

        }
    }


}
