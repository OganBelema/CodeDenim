package com.example.ogan.codedenim.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ogan.codedenim.R
import com.example.ogan.codedenim.gson.ForumAnswer
import java.util.*

/**
 * Created by belema on 1/24/18.
 */
class ForumReplyAdapter(private val forumAnswers: ArrayList<ForumAnswer>?) : RecyclerView.Adapter<ForumReplyAdapter.ForumAnswerHolder>() {
    private lateinit var context: Context

    override fun onBindViewHolder(holder: ForumAnswerHolder, position: Int) {

        forumAnswers?.let {
            val answer = forumAnswers[holder.adapterPosition].Answer
            val user = forumAnswers[holder.adapterPosition].UserId

            holder.answer.text = answer
            holder.answeredBy.text = context.resources.getString(R.string.answer_by, user)

        }
    }

    override fun getItemCount(): Int {
        return forumAnswers?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForumAnswerHolder {
        return ForumAnswerHolder(LayoutInflater
                .from(parent?.context).inflate(R.layout.reply_layout, parent, false))
    }

    inner class ForumAnswerHolder(view: View) : RecyclerView.ViewHolder(view) {
        val answer: TextView
        val answeredBy: TextView

        init {
            context = view.context
            answer = view.findViewById(R.id.answer_tv)
            answeredBy = view.findViewById(R.id.answered_by_tv)
        }
    }

}