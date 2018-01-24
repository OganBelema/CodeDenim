package com.example.ogan.codedenim.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ogan.codedenim.R
import com.example.ogan.codedenim.ReplyForumActivity
import com.example.ogan.codedenim.gson.ForumQuestion
import java.util.*

/**
 * Created by belema on 1/4/18.
 */
class ForumQuestionAdapter(private val forumQuestions: ArrayList<ForumQuestion>?) : RecyclerView.Adapter<ForumQuestionAdapter.ForumQuestionHolder>() {
    private lateinit var context: Context

    override fun onBindViewHolder(holder: ForumQuestionHolder, position: Int) {



        forumQuestions?.let {
            val questionTitle = forumQuestions[holder.adapterPosition].Title
            val questionName = forumQuestions[holder.adapterPosition].QuestionName
            val firstName = forumQuestions[holder.adapterPosition].FirstName
            val lastName = forumQuestions[holder.adapterPosition].LastName
            val postTime = forumQuestions[holder.adapterPosition].PostDate
            val commentNo = forumQuestions[holder.adapterPosition].Count
            val questionId = forumQuestions[holder.adapterPosition].ForumQuestionId

            holder.questionTitle.text = questionTitle
            holder.questionName.text = questionName
            holder.askedBy.text = context.resources.getString(R.string.asked_by, firstName, lastName)
            holder.postTime.text = postTime
            holder.commentNo.text = context.resources.getString(R.string.comments, commentNo)
            holder.questionCard.setOnClickListener {
                val intent = Intent(context, ReplyForumActivity::class.java)
                intent.putExtra("questionName", questionName)
                intent.putExtra("questionId", questionId)
                context.startActivity(intent)

            }
        }
    }

    override fun getItemCount(): Int {
        return forumQuestions?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForumQuestionHolder {
        return ForumQuestionHolder(LayoutInflater
                .from(parent?.context).inflate(R.layout.forum_layout, parent, false))
    }

    inner class ForumQuestionHolder(view: View) : RecyclerView.ViewHolder(view) {
        val questionTitle: TextView
        val questionName: TextView
        val askedBy: TextView
        val questionCard: LinearLayout
        val postTime: TextView
        val commentNo: TextView

        init {
            context = view.context
            questionTitle = view.findViewById(R.id.question_title)
            questionName = view.findViewById(R.id.question_name)
            questionCard = view.findViewById(R.id.question_card)
            askedBy = view.findViewById(R.id.asked_by_txt)
            postTime = view.findViewById(R.id.post_time)
            commentNo = view.findViewById(R.id.comment_no)
        }
    }

}