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
import com.example.ogan.codedenim.courses.TopicActivity
import com.example.ogan.codedenim.gson.Module
import java.util.*

/**
 * Created by belema on 9/7/17.
 */

class ModuleAdapter(private val moduleResult: ArrayList<Module>?) : RecyclerView.Adapter<ModuleAdapter.ModuleVH>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleAdapter.ModuleVH {

        return ModuleVH(LayoutInflater.from(
                parent.context).inflate(R.layout.module_view, parent, false))

    }


    override fun onBindViewHolder(holder: ModuleAdapter.ModuleVH, position: Int) {

        moduleResult?.let {
            val moduleName = moduleResult[position].ModuleName
            holder.moduleName.text = context.resources.getString(R.string.module_name, moduleName)

            val moduleEstimatedTime = moduleResult[position].ExpectedTime.toString()
            holder.moduleEstimatedTime.text = context.resources.getString(R.string.module_expected_time, moduleEstimatedTime)

            val moduleId = moduleResult[position].ModuleId

            holder.cardView.setOnClickListener {
                val intent = Intent(context, TopicActivity::class.java)
                intent.putExtra("moduleId", moduleId)
                context.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int {
        return moduleResult?.size ?: 0
    }

    //View Holders

    inner class ModuleVH(view: View) : RecyclerView.ViewHolder(view) {

        var moduleName: TextView
        var moduleEstimatedTime: TextView
        var cardView: CardView

        init {
            context = view.context
            cardView = view.findViewById(R.id.module_card)
            moduleName = view.findViewById(R.id.moduleName_txt)
            moduleEstimatedTime = view.findViewById(R.id.moduleEstimatedTime_txt)

        }
    }


}
