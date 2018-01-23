package com.example.ogan.codedenim

import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_learning_path_detail.*

class LearningPathDetailActivity : AppCompatActivity() {

    private var categoryId: Int = 0
    private var learningPathName: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning_path_detail)

        val image = findViewById<ImageView>(R.id.learningPathPic_img)
        val name = findViewById<TextView>(R.id.learningPathName_tv)
        val description = findViewById<TextView>(R.id.learningPathDescription_tv)
        val amount = findViewById<TextView>(R.id.learningPathPrice_tv)

        val intent = intent
        val imagePath = intent.getStringExtra("ImageLocation")
        learningPathName = intent.getStringExtra("CategoryName")
        val learningPathDesc = intent.getStringExtra("CategoryDescription") ?: "No description"
        val learningPathPrice = intent.getStringExtra("CategoryPrice")
        categoryId = intent.getIntExtra("CategoryId", 0)
        title = learningPathName

        Picasso.with(applicationContext).load(imagePath).into(image)
        name.text = learningPathName
        description.text = resources.getString(R.string.learning_path_desc, learningPathDesc)
        amount.text = resources.getString(R.string.learning_path_price, learningPathPrice)
        learningPath_enroll_btn.text =  resources.getString(R.string.learning_path_pay_button, learningPathPrice)

        //val user = UserSessionManager.getUserDetails()
        //val email = user[UserSessionManager.KEY_EMAIL]

        learningPath_enroll_btn.setOnClickListener {
            val url = "http://codedenim.azurewebsites.net/CourseCategories/CategoryDetails/$categoryId"
            val builder = CustomTabsIntent.Builder()
            builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
            val customTabIntent = builder.build()
            customTabIntent.launchUrl(this, Uri.parse(url))
            finish()
        }


    }
}
