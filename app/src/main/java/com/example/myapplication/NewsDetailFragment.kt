package com.example.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class NewsDetailFragment : Fragment() {
    private var rootView: View? = null
    var llNewsDetail: LinearLayout? = null
    var tvNewsTitle: TextView? = null
    var tvNewsContent: TextView? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = LayoutInflater.from(context).inflate(R.layout.fragment_news_detail, container, false)
        llNewsDetail = rootView!!.findViewById(R.id.ll_news_detail)
        tvNewsTitle = rootView!!.findViewById(R.id.tv_news_title)
        tvNewsContent = rootView!!.findViewById(R.id.tv_news_content)
        return rootView
    }

    fun setPageData(newsTitle: String, newsContent: String) {
        llNewsDetail!!.visibility = View.VISIBLE
        tvNewsTitle!!.text = newsTitle
        tvNewsContent!!.text = newsContent
    }
}