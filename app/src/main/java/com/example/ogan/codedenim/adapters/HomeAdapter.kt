package com.example.ogan.codedenim.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ogan.codedenim.R
import com.example.ogan.codedenim.adapters.HomeAdapter.HomeViewHolder
import com.example.ogan.codedenim.gson.LearningPath
import com.squareup.picasso.Picasso
import java.util.*

/**
 * Created by belema on 10/29/17.
 */

class HomeAdapter(private val categoryResult: ArrayList<LearningPath>?,private val clickListener: ClickListener) : RecyclerView.Adapter<HomeViewHolder>() {

    private lateinit var context: Context

    companion object {
        var mClickListener: ClickListener? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeViewHolder {
        val v = LayoutInflater.from(
                parent?.context).inflate(R.layout.home_category_layout, parent, false)
        return HomeViewHolder(v)
    }

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) {

        mClickListener = clickListener

        categoryResult?.let{
            holder.bind(categoryResult[position])
            holder.itemView.setOnClickListener {
                if (mClickListener != null)
                    mClickListener?.onBtnClick(position)
            }
        }

    }

    override fun getItemCount(): Int {
        return categoryResult?.size ?: 0
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView: ImageView
        private val textView: TextView

        init {
            context = itemView.context
            imageView = itemView.findViewById(R.id.home_category_image)
            textView = itemView.findViewById(R.id.home_category_name)
        }

        fun bind(item: LearningPath) {
            textView.text = item.CategoryName
            println(item.ImageLocation)
            Picasso.with(context).load("http://codedenim.azurewebsites.net/MaterialUpload/${item.ImageLocation}").into(imageView)
        }
    }

    interface ClickListener {
        fun onBtnClick(position: Int)
    }
}
