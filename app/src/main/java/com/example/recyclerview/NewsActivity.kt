package com.example.recyclerview

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.widget.ImageView
import android.widget.TextView

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val heading = intent.getStringExtra("heading")
        val newsContent = intent.getStringExtra("newsContent")
        val imageId = intent.getIntExtra("imageId", R.drawable.img1)

        val headingTv = findViewById<TextView>(R.id.newTitle)
        val headingImg = findViewById<ImageView>(R.id.newsImg)
        val newsContentTv = findViewById<TextView>(R.id.newsContent)

        headingTv.text = heading
        newsContentTv.text = newsContent
        headingImg.setImageResource(imageId)


        // Change status bar text color here
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // For Android 11 (API level 30) and higher
            window.decorView.windowInsetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // For versions prior to Android 11 (API level 30)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }


    }
}