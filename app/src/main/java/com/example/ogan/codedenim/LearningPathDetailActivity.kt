package com.example.ogan.codedenim

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.ogan.codedenim.fragments.PaymentFragment
import com.squareup.picasso.Picasso

class LearningPathDetailActivity : AppCompatActivity() {

    private var categoryId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning_path_detail)

        val image = findViewById<ImageView>(R.id.learningPathPic_img)
        val name = findViewById<TextView>(R.id.learningPathName_tv)
        val description = findViewById<TextView>(R.id.learningPathDescription_tv)
        val amount = findViewById<TextView>(R.id.learningPathPrice_tv)
        val enrollBtn = findViewById<Button>(R.id.learningPath_enroll_btn)

        val intent = intent
        val imagePath = intent.getStringExtra("ImageLocation")
        val learningPathName = intent.getStringExtra("CategoryName")
        val learningPathDesc = intent.getStringExtra("CategoryDescription")
        val learningPathPrice = intent.getStringExtra("CategoryPrice")
        val studentType = intent.getStringExtra("StudentType")
        categoryId = intent.getIntExtra("CategoryId", 0)
        title = learningPathName

        Picasso.with(applicationContext).load(imagePath).into(image)
        name.text = learningPathName
        description.text = resources.getString(R.string.learning_path_desc, learningPathDesc)
        amount.text = resources.getString(R.string.learning_path_price, learningPathPrice)
        if (studentType == resources.getString(R.string.corper)) {
            enrollBtn.text = resources.getString(R.string.enroll)

            enrollBtn.setOnClickListener {
                /*Intent intent = new Intent(getApplicationContext(), CoursesByCategories.class);
                    intent.putExtra("CategoryId", categoryId);
                    intent.putExtra("CategoryName", learningPathName);
                    startActivity(intent);*/
                val newFragment = PaymentFragment()
                newFragment.show(supportFragmentManager, "missiles")
            }

        }//TODO: change this to check if student has enrolled


    }
}
