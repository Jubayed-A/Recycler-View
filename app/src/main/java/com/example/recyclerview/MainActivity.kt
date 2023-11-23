package com.example.recyclerview

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.MyAdapter.onItemClickListener
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsArrayList: ArrayList<News>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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


        recyclerView = findViewById(R.id.recyclerView)

        // news image declared
        val newsImageArray = arrayOf(
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
        )

        // news headline declared
        val newsHeadingArray = arrayOf(
            "U.K. Foreign Secretary James Cleverly raises issue of BBC tax searches with EAM Jaishankar",
            "Cooking gas prices hiked by ₹50 for domestic, ₹350.50 for commercial cylinders",
            "Joe Biden appoints two prominent Indian-American corporate leaders to his Export Council",
            "Sergey Lavrov will raise suspected bombing of Nord Stream II at G20: Russian Foreign Ministry",
            "Belarusian leader Lukashenko visits China amid Ukraine tensions",
            "China rips new U.S. House committee on countering Beijing",
            "Largest gathering of Foreign Ministers hosted by any G20 presidency: Foreign Secretary Vinay Kwatra"
        )

        val newsContentArray = arrayOf(
            getString(R.string.news_content),
            getString(R.string.news_content),
            getString(R.string.news_content),
            getString(R.string.news_content),
            getString(R.string.news_content),
            getString(R.string.news_content),
        )

        // to set behave of item inside recyclerview, vertically scrolling, horizontally scrolling, uniform grid
        recyclerView.layoutManager = LinearLayoutManager(this)

        // initialized news array list
        newsArrayList = arrayListOf<News>()

        // add all data one by one to newsArrayList
        for (index in newsImageArray.indices) {
            val news = News(newsHeadingArray[index], newsImageArray[index], newsContentArray[index])
            newsArrayList.add(news)
        }

        // adapter code here
        val myAdapter = MyAdapter(newsArrayList, this)
        recyclerView.adapter = myAdapter

        myAdapter.setOnItemClickListener(object : onItemClickListener {
            override fun onItemClicking(position: Int) {
                // on clicking each item, what action do you want to perform
                val intentNews = Intent(this@MainActivity, NewsActivity::class.java)
                intentNews.putExtra("heading", newsArrayList[position].newsHeading)
                intentNews.putExtra("imageId", newsArrayList[position].newsImage)
                intentNews.putExtra("newsContent", newsArrayList[position].newsContent)
                startActivity(intentNews)
            }

        })

    }
}