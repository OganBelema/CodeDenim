package com.example.ogan.codedenim.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ogan.codedenim.R
import com.example.ogan.codedenim.fragments.AudioActivity
import com.example.ogan.codedenim.fragments.PDFActivity
import com.example.ogan.codedenim.fragments.VideoActivity
import com.example.ogan.codedenim.gson.MaterialUpload
import java.util.*

/**
 * Created by belema on 11/20/17.
 */

class MaterialUploadAdapter(private val materialUploads: ArrayList<MaterialUpload>?) : RecyclerView.Adapter<MaterialUploadAdapter.MaterialViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
        return MaterialViewHolder(LayoutInflater.from(
                parent.context).inflate(R.layout.topic_content_layout, parent, false))
    }

    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {
        materialUploads?.let {
            val description = materialUploads[position].Description ?: "No title"
            val fileLocation = materialUploads[position].FileLocation

            holder.description.text = description
            holder.fileType.text = fileLocation

            holder.cardView.setOnClickListener {
                val intent: Intent
                val fileType = materialUploads[holder.adapterPosition].FileType
                println(fileType)
                when (fileType) {
                    "PDF" -> {
                        intent = Intent(context, PDFActivity::class.java)
                        intent.putExtra("fileLocation", fileLocation)
                        intent.putExtra("name", description)
                        context.startActivity(intent)
                    }
                    "MP4" -> {
                        intent = Intent(context, VideoActivity::class.java)
                        intent.putExtra("fileLocation", fileLocation)
                        intent.putExtra("name", description)
                        context.startActivity(intent)
                    }
                    "MP3" -> {
                        intent = Intent(context, AudioActivity::class.java)
                        intent.putExtra("fileLocation", fileLocation)
                        intent.putExtra("name", description)
                        context.startActivity(intent)
                        /*intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse("https://codedenim.azurewebsites.net/MaterialUpload/$fileLocation")
                        context.startActivity(intent)*/
                    }
                    "Audio" -> {
                        intent = Intent(context, AudioActivity::class.java)
                        intent.putExtra("fileLocation", fileLocation)
                        intent.putExtra("name", description)
                        context.startActivity(intent)
                        /*intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse("https://codedenim.azurewebsites.net/MaterialUpload/$fileLocation")
                        context.startActivity(intent)*/
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return materialUploads?.size ?: 0
    }

    inner class MaterialViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val description: TextView
         val fileType: TextView
         val cardView: CardView

        init {
            context = itemView.context
            description = itemView.findViewById(R.id.tv_topic_name)
            fileType = itemView.findViewById(R.id.tv_file_type)
            cardView = itemView.findViewById(R.id.topic_content_card)
        }
    }
}
