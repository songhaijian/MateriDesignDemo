package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

class NewsDetailActivity : AppCompatActivity() {
    var newsDetailFragment: NewsDetailFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        var newsTitle = intent.getStringExtra("newsTitle")
        var newsContent = intent.getStringExtra("newsContent")
        newsDetailFragment = supportFragmentManager.findFragmentById(R.id.fg_news_detail) as NewsDetailFragment
        newsDetailFragment!!.setPageData(newsTitle, newsContent)
    }

    companion object {
        fun jumpPage(mContext: Context, newsTitle: String, newsContent: String) {
            var jumpIntent = Intent(mContext, NewsDetailActivity::class.java)
            jumpIntent.putExtra("newsTitle", newsTitle)
            jumpIntent.putExtra("newsContent", newsContent)
            mContext.startActivity(jumpIntent)
        }
    }
}